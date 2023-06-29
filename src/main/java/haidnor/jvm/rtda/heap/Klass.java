package haidnor.jvm.rtda.heap;

import haidnor.jvm.classloader.ClassLoader;
import lombok.SneakyThrows;
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

    public final String superClassName;

    private Klass superKlass;

    @SneakyThrows
    public Klass(ClassLoader classLoader, JavaClass javaClass) {
        this.javaClass = javaClass;
        this.classLoader = classLoader;
        this.className = javaClass.getClassName();
        this.superClassName = javaClass.getSuperclassName();

        JavaClass superJavaClass = javaClass.getSuperClass();
        if (superJavaClass != null) {
            this.superKlass = new Klass(classLoader, superJavaClass);
        }
    }

    public Instance newInstance() {
        List<JavaField> javaFieldList = new ArrayList<>();
        for (Field field : javaClass.getFields()) {
            JavaField javaField = new JavaField(field);
            javaFieldList.add(javaField);
        }
        Instance object = new Instance(javaFieldList, this);
        if (this.superKlass != null) {
            object.setSuperInstance(this.superKlass.newInstance());
        }
        return object;
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
