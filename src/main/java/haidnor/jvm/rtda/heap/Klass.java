package haidnor.jvm.rtda.heap;

import haidnor.jvm.classloader.ClassLoader;
import org.apache.bcel.classfile.Field;
import org.apache.bcel.classfile.JavaClass;

import java.util.ArrayList;
import java.util.List;

/**
 * 类元信息
 */
public class Klass {

    /**
     * BCEL JavaClass
     */
    private final JavaClass javaClass;

    /**
     * 加载此 Class 的类加载器
     */
    private final ClassLoader classLoader;

    /**
     * 此 Class 名称. 例如 java.util.ArrayList
     */
    private final String className;

    public Klass(ClassLoader classLoader, JavaClass javaClass) {
        this.javaClass = javaClass;
        this.classLoader = classLoader;
        this.className = javaClass.getClassName();
    }

    public Instance newInstance() {
        List<JavaField> javaFieldList = new ArrayList<>();
        for (Field field : javaClass.getFields()) {
            JavaField javaField = new JavaField(field);
            javaFieldList.add(javaField);
        }
        return new Instance(javaFieldList, this);
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
