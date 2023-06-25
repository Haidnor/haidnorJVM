package haidnor.jvm.instruction.references;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.util.CodeStream;
import haidnor.jvm.util.ConstantPoolUtil;
import haidnor.jvm.util.SignatureUtil;
import lombok.SneakyThrows;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Utility;

import java.util.Objects;

public class INVOKESTATIC extends Instruction {

    private final int constantMethodrefIndex;

    public INVOKESTATIC(CodeStream codeStream) {
        super(codeStream);
        this.constantMethodrefIndex = codeStream.readShortOperand(this);
    }

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getConstantPool();
        ConstantPoolUtil constantPoolUtil = frame.getConstantPoolUtil();

        ConstantMethodref methodref = constantPool.getConstant(constantMethodrefIndex);

        String className = constantPoolUtil.getBelongClassName(methodref);
        String methodName = constantPoolUtil.getMethodName(methodref);
        String methodSignature = constantPoolUtil.getMethodSignature(methodref);

        // 解析方法签名得到方法的返回类型
        String returnType = Utility.methodSignatureReturnType(methodSignature, false);

        //  系统类反射 自定义类另外处理
        if (className.startsWith("java")) {
            // 执行方法的参数列表
            Class<?>[] parameterTypeArr = SignatureUtil.getParameterTypes(methodSignature);
            // 执行方法的参数值
            Object[] stacksValueArr = frame.popStacksValue(parameterTypeArr.length);

            // 将特定的参数转换为基本类型
            for (int i = 0; i < parameterTypeArr.length; i++) {
                Class<?> aClass = parameterTypeArr[i];
                // boolean 存储方式为 int 类型
                if (aClass.getName().equals("boolean")) {
                    int booleanFlag = (int) stacksValueArr[i];
                    stacksValueArr[i] = booleanFlag == 1;
                }
            }
            // 执行方法的示例对象
            Object object = frame.pop().getValue();
            // 如果 jvm 要执行一个方法, 1.把这个方法对象压入操作数栈 2.把这个方法的参数值压入操作出栈(一个参数占用一个操作数栈帧) 3.执行 INVOKEVIRTUAL
            // 执行 INVOKEVIRTUAL 的流程
            // 1.从操作栈中取出全部参数
            // 2.从操作栈中取出执行方法的对象
            // 3.使用对象执行方法
            java.lang.reflect.Method javaMethod = object.getClass().getMethod(methodName, parameterTypeArr);

            if (Objects.equals(Const.getTypeName(Const.T_VOID), returnType)) {     // void 调用的方法无返回值
                javaMethod.invoke(object, stacksValueArr);
            } else {
                // TODO
                // descriptorStream.pushField(javaMethod.invoke(obj, params), frame);
            }
        } else {

//            JavaNativeInterface.callStaticMethod(mainMethod);
        }
    }

}
