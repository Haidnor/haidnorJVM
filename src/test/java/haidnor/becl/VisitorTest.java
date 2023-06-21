package haidnor.becl;

import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.junit.Test;

public class VisitorTest {

    @Test
    public void test() throws Exception {
        MyVisitor myVisitor = new MyVisitor();

        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class");
        JavaClass javaClass = classParser.parse();
        javaClass.accept(myVisitor);
    }

}
