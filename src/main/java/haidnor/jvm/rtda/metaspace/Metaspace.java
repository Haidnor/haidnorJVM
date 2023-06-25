package haidnor.jvm.rtda.metaspace;

import org.apache.bcel.classfile.JavaClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 元空间
 */
public class Metaspace {

    private static final Map<String, JavaClass> javaClassMapMap = new ConcurrentHashMap<>();

    public static JavaClass getJavaClass(String className) {
        return javaClassMapMap.get(className);
    }

    public static void registerJavaClass(JavaClass javaClass) {
        String className = javaClass.getClassName();
        javaClassMapMap.put(className, javaClass);
    }

}
