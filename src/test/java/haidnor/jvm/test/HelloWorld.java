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

public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("hello,world!");
    }

    @Test
    public void test_() throws Exception {
        ClassParser classParser = new ClassParser(BaseTest.getJavaClassAbsolutePath(HelloWorld.class));
        JavaClass javaClass = classParser.parse();
        Metaspace.registerJavaClass(javaClass);
        Method mainMethod = JavaClassUtil.getMainMethod(javaClass);
        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }
}
