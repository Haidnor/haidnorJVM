package haidnor.jvm.classloader;

import haidnor.jvm.rtda.heap.Class;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

import java.io.IOException;

public class ClassLoader {

    public Class loadClass(String path) throws IOException {
        ClassParser classParser = new ClassParser("D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\HelloWorld.class");
        JavaClass javaClass = classParser.parse();
        return new Class(this, javaClass);
    }

}