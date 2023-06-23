package haidnor.vm;

import haindor.vm.bytecode.ClassFile;
import haindor.vm.classloader.ClassLoader;
import org.junit.Test;

public class ClassLoaderTest {

    @Test
    public void test() throws Exception {
        String path = "D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class";

        ClassLoader classLoader = new ClassLoader();
        ClassFile classFile = classLoader.readClassFile(path);
        System.out.println(classFile);
    }

}
