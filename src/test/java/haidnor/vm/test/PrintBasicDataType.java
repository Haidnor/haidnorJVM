package haidnor.vm.test;

import haidnor.vm.core.JavaNativeInterface;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.JavaClassUtil;
import haidnor.vm.util.JvmThreadHolder;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

/**
 * 测试打印基本数据类型
 */
public class PrintBasicDataType {

    public static void main(String[] args) {
        System.out.println(true);
        System.out.println((byte)1);
        System.out.println((short)1);
        System.out.println(1);
        System.out.println(1L);
        System.out.println(1f);
        System.out.println(1d);
    }

    @Test
    public void test_() throws Exception {
        ClassParser classParser = new ClassParser(BaseTest.getJavaClassAbsolutePath(PrintBasicDataType.class));
        Method mainMethod = JavaClassUtil.getMainMethod(classParser.parse());

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
