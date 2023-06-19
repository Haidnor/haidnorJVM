package haidnor.vm;

import haindor.vm.bytecode.ClassFile;
import haindor.vm.classloader.ClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class ClassLoaderTest {

    @Test
    public void test_() throws Exception {
        String path = "D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class";

        ClassLoader classLoader = new ClassLoader();
        ClassFile classFile = classLoader.readClassFile(path);
        System.out.println(classFile);
    }


}
