package haidnor.jvm.test;

import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.util.JavaClassUtil;
import haidnor.jvm.util.JvmThreadHolder;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

public class IINC_test {

    public static void main(String[] args) {
        int a = -127;
        a++;
        System.out.println(a);

        int b = 129;
        b++;
        System.out.println(b);

        int c = 321;
        c++;
        System.out.println(c);

        int d = 0;
        d++;
        System.out.println(d);
    }

    @Test
    public void test_() throws Exception {
        ClassParser classParser = new ClassParser(BaseTest.getJavaClassAbsolutePath(IINC_test.class));
        Method mainMethod = JavaClassUtil.getMainMethod(classParser.parse());

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
