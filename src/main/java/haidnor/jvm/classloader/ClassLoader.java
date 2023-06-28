package haidnor.jvm.classloader;

import haidnor.jvm.rtda.heap.MetaClass;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

import java.io.IOException;
import java.net.URL;

public class ClassLoader {

    public final String name;

    public ClassLoader(String name) {
        this.name = name;
    }

    /**
     * @param classPath 类路径,例如 haidnor/jvm/classloader/ClassLoader
     */
    public MetaClass loadClass(String classPath) throws IOException {
        URL resource = this.getClass().getResource("/");
        ClassParser classParser = new ClassParser(resource.getPath() + classPath + ".class");
        JavaClass javaClass = classParser.parse();
        return new MetaClass(this, javaClass);
    }

}