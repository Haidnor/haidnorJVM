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

public class IADD extends BaseTest{

    public static void main(String[] args) {
        int c = add(1, 2);
        System.out.println(c);
    }

    public static int add(int a,int b) {
        int c = a + b;
        return c;
    }

    @Test
    public void jvm_test() throws Exception {
        long t1 = System.currentTimeMillis();
        ClassParser classParser = new ClassParser(getJavaClassAbsolutePath(IADD.class));
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
