package haidnor.vm;

import haidnor.vm.core.JavaNativeInterface;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.ThreadHolder;
import haidnor.vm.util.JavaClassUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;
import org.junit.Test;

@Slf4j
public class JvmTest {

    @Test
    public void test_jvm() throws Exception {
        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
        JavaClass javaClass = classParser.parse();
        log.info("{}", javaClass);
        log.info("{}", javaClass.getConstantPool());

        Method mainMethod = JavaClassUtil.getMainMethod(javaClass);

        if (mainMethod == null) {
            throw new Error("无法找到 main 方法");
        }

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        ThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
