package haidnor.jvm.instruction.references;

import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.heap.KlassMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.util.CodeStream;
import haidnor.jvm.util.ConstantPoolUtil;
import haidnor.jvm.util.SignatureUtil;
import lombok.SneakyThrows;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.*;

import java.util.Objects;

public class INVOKESTATIC extends Instruction {

    private final int constantMethodrefIndex;

    public INVOKESTATIC(CodeStream codeStream) {
        super(codeStream);
        this.constantMethodrefIndex = codeStream.readUnsignedShort(this);
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
            java.lang.Class<?>[] parameterTypeArr = SignatureUtil.getParameterTypes(methodSignature);
            Object[] stacksValueArr = frame.popStacksValue(parameterTypeArr.length);

            for (int i = 0; i < parameterTypeArr.length; i++) {
                java.lang.Class<?> aClass = parameterTypeArr[i];
                if (aClass.getName().equals("boolean")) {
                    int booleanFlag = (int) stacksValueArr[i];
                    stacksValueArr[i] = booleanFlag == 1;
                }
            }
            // 执行方法的示例对象
            Object object = frame.pop().getValue();
            java.lang.reflect.Method javaMethod = object.getClass().getMethod(methodName, parameterTypeArr);

            if (Objects.equals(Const.getTypeName(Const.T_VOID), returnType)) {     // void 调用的方法无返回值
                javaMethod.invoke(object, stacksValueArr);
            } else {
                // TODO
                // descriptorStream.pushField(javaMethod.invoke(obj, params), frame);
            }
            return;
        }

        Klass meteKlass = Metaspace.getJavaClass(Utility.compactClassName(className));
        if (meteKlass != null) {
            JavaClass javaClass = meteKlass.getJavaClass();
            for (org.apache.bcel.classfile.Method method : javaClass.getMethods()) {
                if (method.getSignature().equals(methodSignature) && method.getName().equals(methodName)) {
                    KlassMethod klassMethod = new KlassMethod(meteKlass, method);
                    JavaNativeInterface.callMethod(frame, klassMethod);
                    break;
                }
            }
        }

    }

}
