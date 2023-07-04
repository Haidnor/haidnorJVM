package haidnor.jvm.test;

import haidnor.jvm.classloader.ClassLoader;
import haidnor.jvm.core.JavaNativeInterface;
import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.heap.KlassMethod;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.test.instruction.math.ISUB;
import haidnor.jvm.test.instruction.math.LSUB;
import haidnor.jvm.test.instruction.references.NEW;
import haidnor.jvm.util.JavaClassUtil;
import haidnor.jvm.util.JvmThreadHolder;
import lombok.SneakyThrows;
import org.junit.Test;

public class haidnorJVM_TEST {

    @SneakyThrows
    public static void runMainClass(java.lang.Class<?> mainClass) {
        ClassLoader bootClassLoader = new ClassLoader("ApplicationClassLoader");
        Klass mainMeteKlass = bootClassLoader.loadClass(mainClass.getName().replace('.', '/'));
        KlassMethod mainKlassMethod = JavaClassUtil.getMainMethod(mainMeteKlass);
        Metaspace.registerJavaClass(mainMeteKlass);
        JvmThreadHolder.set(new JvmThread());

        JavaNativeInterface.callMainStaticMethod(mainKlassMethod);
    }

    @Test
    public void test_NEW() throws Exception {
        runMainClass(NEW.class);
    }

    @Test
    public void test_ISUB() throws Exception {
        runMainClass(ISUB.class);
    }

    @Test
    public void test_LSUB() throws Exception {
        runMainClass(LSUB.class);
    }


}
