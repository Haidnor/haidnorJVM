package haidnor.jvm.rtda.metaspace;

import haidnor.jvm.rtda.heap.Klass;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 元空间
 */
public class Metaspace {

    private static final Map<String, Klass> javaClassMapMap = new ConcurrentHashMap<>();

    public static Klass getJavaClass(String className) {
        return javaClassMapMap.get(className);
    }

    public static void registerJavaClass(Klass javaKlass) {
        String className = javaKlass.getClassName();
        javaClassMapMap.put(className, javaKlass);
    }

}
