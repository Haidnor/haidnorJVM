package haidnor.jvm.test;

import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.util.JavaClassUtil;
import haidnor.jvm.util.JvmThreadHolder;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

public class CallStaticMethod2Test {

    public static void main(String[] args) {
        float i = staticMethod();
        System.out.println(i);
    }

    public static float staticMethod() {
        return 1321.321f;
    }

    @Test
    public void jvm_test() throws Exception {
        long t1 = System.currentTimeMillis();
        ClassParser classParser = new ClassParser(BaseTest.getJavaClassAbsolutePath(CallStaticMethod2Test.class));
        JavaClass javaClass = classParser.parse();
        Metaspace.registerJavaClass(javaClass);
        Method mainMethod = JavaClassUtil.getMainMethod(javaClass);

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
        long t2 = System.currentTimeMillis();
        System.out.println("执行耗时: " + (t2 - t1) + " ms"); // 189ms
    }

}
