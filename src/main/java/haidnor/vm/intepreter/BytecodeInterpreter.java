package haidnor.vm.intepreter;

import haidnor.vm.bcel.Const;
import haidnor.vm.bcel.classfile.*;
import haidnor.vm.classfile.DescriptorStream2;
import haidnor.vm.memory.StackObj;
import haidnor.vm.runtime.JavaThread;
import haidnor.vm.runtime.JavaVFrame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.InputStreamUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.DataInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class BytecodeInterpreter extends StackObj {


    @SneakyThrows
    public static void run(JavaThread thread, MethodInfo method) {
        // 得到栈帧
        JavaVFrame frame = (JavaVFrame) thread.getStack().peek();

        // 得到字节码指令
        Code code = method.getCode();

        byte[] codes = code.getCode();
        DataInputStream codeStream = InputStreamUtil.getDataInputStream(codes);

        while (codeStream.available() > 0) {
            int codeB = codeStream.read();
            ConstantPool constantPool = method.getConstantPool();
            switch (codeB) {
                case Bytecodes.LDC: {
                    log.info("execute: LDC");

                    // 取出操作数
                    int operand = codeStream.read();
                    Constant constant = constantPool.getConstant(operand);

                    switch (constant.getTag()) {
                        case Const.CONSTANT_String: {
                            ConstantString constantString = (ConstantString) constant;
                            String var = constantPool.getConstantString(constantString.getStringIndex(), Const.CONSTANT_Utf8);
                            frame.getStack().push(new StackValue(Const.T_OBJECT, var));
                            break;
                        }
                    }
                    break;
                }
                case Bytecodes.RETURN: {
                    log.info("execute: RETURN");
                    // pop 出栈帧
                    thread.getStack().pop();
                    log.info("remaining stack frames count: " + thread.getStack().size());
                    break;
                }
                case Bytecodes.GETSTATIC: {
                    // 获取字段符号引用指定的对象或者值(类的静态字段 static 修饰),并将其压入操作数栈
                    log.info("execute: GETSTATIC");

                    // 获取操作数 u2
                    int operand = codeStream.readUnsignedShort();
                    ConstantFieldref constantFieldref = method.getConstantPool().getConstant(operand);

                    // className
                    ConstantClass constantClass = constantPool.getConstant(constantFieldref.getClassIndex());
                    String className = constantPool.getConstantString(constantClass.getNameIndex(), Const.CONSTANT_Utf8);

                    // fieldName
                    ConstantNameAndType constantNameAndType = constantPool.getConstant(constantFieldref.getNameAndTypeIndex());
                    String fieldName = constantPool.getConstantString(constantNameAndType.getNameIndex(), Const.CONSTANT_Utf8);
                    Class<?> clazz = Class.forName(className.replace('/', '.'));
                    Field field = clazz.getField(fieldName);
                    Object obj = field.get(null);       // 获取静态字段上的值
                    System.out.println();
                    frame.getStack().push(new StackValue(Const.T_OBJECT, obj));
                    break;
                }
                case Bytecodes.INVOKEVIRTUAL: {
                    // 调用所有虚方法
                    log.info("execute: INVOKEVIRTUAL");

                    int operand = codeStream.readUnsignedShort(); // u2

                    ConstantMethodref constantMethodref = constantPool.getConstant(operand);

                    ConstantClass constantClass = constantPool.getConstant(constantMethodref.getClassIndex());
                    String className = constantPool.getConstantString(constantClass.getNameIndex(), Const.CONSTANT_Utf8);

                    ConstantNameAndType constantNameAndType = constantPool.getConstant(constantMethodref.getNameAndTypeIndex());
                    String methodName = constantPool.getConstantString(constantNameAndType.getNameIndex(), Const.CONSTANT_Utf8);
                    String descriptorName = constantPool.getConstantString(constantNameAndType.getSignatureIndex(), Const.CONSTANT_Utf8);


                    //  系统类反射 自定义类另外处理
                    if (className.startsWith("java")) {
                        DescriptorStream2 descriptorStream = new DescriptorStream2(descriptorName);
                        descriptorStream.parseMethod();

                        Object[] params = descriptorStream.getParamsVal(frame);
                        Class[] paramsClass = descriptorStream.getParamsType();

                        Object obj = frame.getStack().pop().getObject();

                        Method fun = obj.getClass().getMethod(methodName, paramsClass);
                        /**
                         * 处理：
                         *  1、无返回值
                         *  2、有返回值
                         */
                        if (Const.T_VOID == descriptorStream.getReturnElement().getType()) {
                            fun.invoke(obj, params);
                        } else {
                            descriptorStream.pushField(fun.invoke(obj, params), frame);
                        }
                    }
                    break;
                }
                default: {
                    throw new Error("无效指令");
                }
            }
        }
    }

}