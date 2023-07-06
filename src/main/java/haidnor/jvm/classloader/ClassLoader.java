package haidnor.jvm.classloader;

import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.metaspace.Metaspace;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;

import java.io.File;
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
    public Klass loadClass(String classPath) throws IOException {
        ClassParser classParser;
        if (classPath.startsWith("java/")) {
            String rtJarPath = getRtJarPath();

            if (!new File(rtJarPath).exists()) {
                throw new IllegalStateException("rt.jar not found");
            }
            classParser = new ClassParser(rtJarPath, classPath + ".class");
        } else {
            URL resource = this.getClass().getResource("/");
            String fileName = resource.getPath() + classPath + ".class";
            classParser = new ClassParser(fileName);
        }

        JavaClass javaClass = classParser.parse();
        Klass klass = new Klass(this, javaClass);
        Metaspace.registerJavaClass(klass);
        return klass;
    }

    public Klass loadClassWithAbsolutePath(String absolutePath) throws IOException {
        ClassParser classParser = new ClassParser(absolutePath);
        JavaClass javaClass = classParser.parse();
        Klass klass = new Klass(this, javaClass);
        Metaspace.registerJavaClass(klass);
        return klass;
    }

    private String getRtJarPath() {
        // String javaHome = System.getenv("JAVA_HOME");
        //  Path rtJarPath = Paths.get(javaHome, "jre", "lib", "rt.jar");
        return "D:/Program Files/Java/jdk1.8.0_361/jre/lib/rt.jar";
    }

}