package haidnor.jvm.instruction.references;

import haidnor.jvm.classloader.ClassLoader;
import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.rtda.heap.MetaClass;
import haidnor.jvm.rtda.heap.MetaMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.util.CodeStream;
import haidnor.jvm.util.ConstantPoolUtil;
import lombok.SneakyThrows;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

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

        MetaClass meteClass = Metaspace.getJavaClass(className);
        JavaClass javaClass;
        if (meteClass != null) {
            javaClass = meteClass.getJavaClass();
        } else {
            ClassLoader classLoader = frame.getMetaClass().getClassLoader();
            MetaClass metaClass = classLoader.loadClass(className);
            javaClass = metaClass.getJavaClass();
        }

        for (Method method : javaClass.getMethods()) {
            if (method.getSignature().equals(methodSignature) && method.getName().equals(methodName)) {
                MetaMethod metaMethod = new MetaMethod(meteClass, method);
                JavaNativeInterface.callStaticMethod(frame, metaMethod);
                break;
            }
        }
    }


}
