package haidnor.jvm.rtda.metaspace;

import haidnor.jvm.rtda.heap.MetaClass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 元空间
 */
public class Metaspace {

    private static final Map<String, MetaClass> javaClassMapMap = new ConcurrentHashMap<>();

    public static MetaClass getJavaClass(String className) {
        return javaClassMapMap.get(className);
    }

    public static void registerJavaClass(MetaClass javaClass) {
        String className = javaClass.getClassName();
        javaClassMapMap.put(className, javaClass);
    }

}
