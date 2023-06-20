package haidnor.vm;

import haindor.vm.bytecode.ClassFile;
import haindor.vm.classloader.ClassLoader;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
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


    @Test
    public void test_1() throws Exception {
//        Repository.setRepository(Repository.getRepository());
//        ClassPath.ClassFile classFile = Repository.lookupClassFile("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
//        System.out.println(classFile);

        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
        JavaClass parse = classParser.parse();
        System.out.println(parse);
    }

}
