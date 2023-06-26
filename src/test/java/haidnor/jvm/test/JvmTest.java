package haidnor.jvm.test;

import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.util.JvmThreadHolder;
import haidnor.jvm.util.JavaClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

@Slf4j
public class JvmTest {

    @Test
    public void test_jvm() throws Exception {
        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\HelloWorld.class");
        JavaClass javaClass = classParser.parse();
        log.debug("{}", javaClass);
        log.debug("{}", javaClass.getConstantPool());

        Method mainMethod = JavaClassUtil.getMainMethod(javaClass);

        if (mainMethod == null) {
            throw new Error("无法找到 main 方法");
        }

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        JvmThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
