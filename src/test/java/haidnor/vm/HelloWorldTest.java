package haidnor.vm;

import haidnor.vm.core.JavaNativeInterface;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.JavaClassUtil;
import haidnor.vm.util.ThreadHolder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

import java.net.URL;

@Slf4j
public class HelloWorldTest {

    @SneakyThrows
    public static void main(String[] args) {
        URL resource = HelloWorldTest.class.getResource("/HelloWorld.class");
        ClassParser classParser = new ClassParser(resource.getPath());
        JavaClass javaClass = classParser.parse();

        log.debug("{}", javaClass);
        log.debug("{}", javaClass.getConstantPool());

        Method mainMethod = JavaClassUtil.getMainMethod(javaClass);

        // JVM main thread
        JvmThread mainThread = new JvmThread();
        ThreadHolder.set(mainThread);

        // 执行main方法
        JavaNativeInterface.callStaticMethod(mainMethod);
    }

}
