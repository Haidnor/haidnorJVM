package haidnor.jvm.instruction.references;

import haidnor.jvm.classloader.ClassLoader;
import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.heap.KlassMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.util.CodeStream;
import haidnor.jvm.util.ConstantPoolUtil;
import lombok.SneakyThrows;
import org.apache.bcel.classfile.*;

public class INVOKESPECIAL extends Instruction {

    private final int constantMethodrefIndex;

    public INVOKESPECIAL(CodeStream codeStream) {
        super(codeStream);
        this.constantMethodrefIndex = codeStream.readUnsignedShort(this);
    }

    @SneakyThrows
    @Override
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getConstantPool();
        ConstantPoolUtil constantPoolUtil = frame.getConstantPoolUtil();
        ConstantMethodref methodref = constantPool.getConstant(constantMethodrefIndex);

        String className = constantPoolUtil.getBelongClassName(methodref);
        String methodName = constantPoolUtil.getMethodName(methodref);
        String methodSignature = constantPoolUtil.getMethodSignature(methodref);

        Klass klass = Metaspace.getJavaClass(Utility.compactClassName(className));
        JavaClass javaClass;
        if (klass != null) {
            javaClass = klass.getJavaClass();
        } else {
            ClassLoader classLoader = frame.getMetaClass().getClassLoader();
            klass = classLoader.loadClass(className);
            javaClass = klass.getJavaClass();
        }

        for (Method method : javaClass.getMethods()) {
            if (method.getSignature().equals(methodSignature) && method.getName().equals(methodName)) {
                KlassMethod klassMethod = new KlassMethod(klass, method);
                JavaNativeInterface.callMethod(frame, klassMethod);
                break;
            }
        }
    }


}
