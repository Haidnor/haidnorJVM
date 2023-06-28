package haidnor.jvm.test;

import haidnor.jvm.classloader.ClassLoader;
import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.rtda.heap.MetaClass;
import haidnor.jvm.rtda.heap.MetaMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.test.instruction.references.NEW;
import haidnor.jvm.util.JavaClassUtil;
import haidnor.jvm.util.JvmThreadHolder;
import lombok.SneakyThrows;
import org.junit.Test;

public class haidnorJVM_TEST {

    @SneakyThrows
    public static void runMainClass(Class<?> mainClass) {
        ClassLoader bootClassLoader = new ClassLoader("boot");
        MetaClass mainMeteClass = bootClassLoader.loadClass(mainClass.getName().replace('.', '/'));
        MetaMethod mainMethod = JavaClassUtil.getMainMethod(mainMeteClass);
        Metaspace.registerJavaClass(mainMeteClass);
        JvmThreadHolder.set(new JvmThread());

        JavaNativeInterface.callMainStaticMethod(mainMethod);
    }

    @Test
    public void test_NEW() throws Exception {
        runMainClass(NEW.class);
    }

}
