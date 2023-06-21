package haidnor.vm;

import haidnor.vm.bcel.classfile.ClassParser;
import haidnor.vm.bcel.classfile.JavaClass;
import haidnor.vm.bcel.classfile.MethodInfo;
import haidnor.vm.prims.JavaNativeInterface;
import haidnor.vm.runtime.JavaThread;
import haidnor.vm.runtime.Threads;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ClassLoaderTest {

    @Test
    public void test_() throws Exception {
        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
        JavaClass javaClass = classParser.parse();
        log.info("{}", javaClass);

        MethodInfo mainMethod = javaClass.getMainMethod();

        // 创建线程
        JavaThread thread = new JavaThread();

        Threads.getThreadList().add(thread);
        Threads.setCurrentThread(thread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
