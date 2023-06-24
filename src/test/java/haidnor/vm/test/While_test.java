package haidnor.vm.test;

import haidnor.vm.core.JavaNativeInterface;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.JavaClassUtil;
import haidnor.vm.util.JvmThreadHolder;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

public class While_test {

    public static void main(String[] args) {
        int a = 1;
        while (a < 10000) {
            System.out.println(a);
            a++;
        }
    }

    @Test
    public void test_() throws Exception {
        long l = System.currentTimeMillis();
        ClassParser classParser = new ClassParser(BaseTest.getJavaClassAbsolutePath(While_test.class));
        Method mainMethod = JavaClassUtil.getMainMethod(classParser.parse());

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
        long l1 = System.currentTimeMillis();
        System.out.println(l1 -l);
    }

}
