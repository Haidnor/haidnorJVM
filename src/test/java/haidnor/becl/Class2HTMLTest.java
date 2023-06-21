package haidnor.becl;

import haidnor.vm.bcel.classfile.ClassParser;
import haidnor.vm.bcel.classfile.JavaClass;
import haidnor.vm.bcel.util.Class2HTML;
import org.junit.Test;

public class Class2HTMLTest {

    @Test
    public void test_() throws Exception {
        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
        JavaClass javaClass = classParser.parse();

        Class2HTML class2HTML = new Class2HTML(javaClass, "R:/html/");
    }

}
