package haidnor.jvm.rtda.heap;

import haidnor.jvm.classloader.ClassLoader;
import org.apache.bcel.classfile.JavaClass;

/**
 * 每个类的 Class 对象
 */
public class MetaClass {

    private final JavaClass javaClass;

    private final ClassLoader classLoader;

    private final String className;

    public MetaClass(ClassLoader classLoader, JavaClass javaClass) {
        this.javaClass = javaClass;
        this.classLoader = classLoader;
        this.className = javaClass.getClassName();
//        int accessFlags = javaClass.getAccessFlags();
//        String superClassClassName = javaClass.getSuperClass().getClassName(); // java.lang.Object
//        JavaClass[] allInterfaces = javaClass.getAllInterfaces();
//        Field[] fields = javaClass.getFields();
//        new Class(javaClass);
    }


    public Instance newInstance() {
        Instance instance = new Instance(this);
        return instance;
    }

    public JavaClass getJavaClass() {
        return javaClass;
    }

    public ClassLoader getClassLoader() {
        return classLoader;
    }

    public String getClassName() {
        return className;
    }
}
