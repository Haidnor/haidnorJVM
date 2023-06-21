package haidnor.vm.core;

import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.ConstantPoolUtil;
import haidnor.vm.util.IoUtil;
import haidnor.vm.util.SignatureUtil;
import haidnor.vm.util.ThreadHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.*;

import java.io.DataInputStream;
import java.lang.reflect.Field;
import java.util.Objects;

@Slf4j
public class Interpreter {

    @SneakyThrows
    public static void run(Method method) {
        JvmThread jThread = ThreadHolder.get();
        Frame frame = jThread.peek();

        DataInputStream codeStream = IoUtil.getDataInputStream(method.getCode().getCode());
        ConstantPool constantPool = method.getConstantPool();
        ConstantPoolUtil constantPoolUtil = new ConstantPoolUtil(constantPool);

        while (codeStream.available() > 0) {
            switch (codeStream.read()) {
                case Const.LDC -> {
                    log.info("execute: LDC");   // 将值压入操作数栈

                    // 从常量池中获取值
                    int operand = codeStream.read();
                    Constant constant = constantPool.getConstant(operand);

                    switch (constant.getTag()) {
                        case Const.CONSTANT_String: {
                            ConstantString constString = (ConstantString) constant;
                            Object value = constString.getConstantValue(constantPool);
                            frame.push(new StackValue(Const.T_OBJECT, value));
                            break;
                        }
                    }
                }
                case Const.RETURN -> {
                    log.info("execute: RETURN");    // pop 出栈帧

                    jThread.pop();
                    log.info("jvm thread stack size:" + jThread.stackSize());
                }
                case Const.GETSTATIC -> {
                    log.info("execute: GETSTATIC");    // 获取字段符号引用指定的对象或者值(类的静态字段 static 修饰),并将其压入操作数栈

                    // 获取操作数 u2
                    int operand = codeStream.readUnsignedShort();
                    ConstantFieldref constFieldref = constantPool.getConstant(operand);

                    // 字段所属的 Java 类
                    String className = constantPoolUtil.getBelongClassName(constFieldref);
                    String fieldName = constantPoolUtil.getFieldName(constFieldref);

                    Class<?> clazz = Class.forName(className.replace('/', '.'));
                    Field field = clazz.getField(fieldName);
                    Object staticFiledValue = field.get(null);       // 获取静态字段上的值

                    frame.push(new StackValue(Const.T_OBJECT, staticFiledValue));
                }
                case Const.INVOKEVIRTUAL -> {
                    log.info("execute: INVOKEVIRTUAL"); // 调用所有虚方法
                    int methodrefIndex = codeStream.readUnsignedShort();

                    ConstantMethodref methodref = constantPool.getConstant(methodrefIndex);

                    String className = constantPoolUtil.getBelongClassName(methodref);
                    String methodName = constantPoolUtil.getMethodName(methodref);
                    String methodSignature = constantPoolUtil.getMethodSignature(methodref);

                    // 解析方法签名得到方法的返回类型
                    String returnType = Utility.methodSignatureReturnType(methodSignature, false);

                    //  系统类反射 自定义类另外处理
                    if (className.startsWith("java")) {
                        // 执行方法的参数列表
                        Class<?>[] parameterTypes = SignatureUtil.getParameterTypes(methodSignature);
                        // 执行方法的参数值
                        Object[] params = frame.popObjets(parameterTypes.length);
                        // 执行方法的示例对象
                        Object object = frame.pop().getValue();

                        // 如果 jvm 要执行一个方法, 1.把这个方法对象压入操作数栈 2.把这个方法的参数值压入操作出栈(一个参数占用一个操作数栈帧) 3.执行 INVOKEVIRTUAL
                        // 执行 INVOKEVIRTUAL 的流程
                        // 1.从操作栈中取出全部参数
                        // 2.从操作栈中取出执行方法的对象
                        // 3.使用对象执行方法

                        java.lang.reflect.Method javaMethod = object.getClass().getMethod(methodName, parameterTypes);

                        if (Objects.equals(Const.getTypeName(Const.T_VOID), returnType)) {     // void 调用的方法无返回值
                            javaMethod.invoke(object, params);
                        } else {
                            // TODO
                            // descriptorStream.pushField(javaMethod.invoke(obj, params), frame);
                        }
                    }
                }
                default -> throw new Error("无效指令");
            }
        }
    }

}