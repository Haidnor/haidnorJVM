package haidnor.jvm.rtda.heap;

import haidnor.jvm.classloader.ClassLoader;
import org.apache.bcel.classfile.JavaClass;

public class Class {

    private final JavaClass javaClass;

    private final ClassLoader classLoader;

    public Class(ClassLoader classLoader, JavaClass javaClass) {
        this.javaClass = javaClass;
        this.classLoader = classLoader;
    }

}
