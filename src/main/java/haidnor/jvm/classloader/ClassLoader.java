package haidnor.jvm.classloader;

import haidnor.jvm.rtda.heap.Klass;
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
            classParser = new ClassParser(resource.getPath() + classPath + ".class");
        }

        JavaClass javaClass = classParser.parse();
        return new Klass(this, javaClass);
    }

    public static String getRtJarPath() {
        // String javaHome = System.getenv("JAVA_HOME");
        //  Path rtJarPath = Paths.get(javaHome, "jre", "lib", "rt.jar");
        return  "D:/Program Files/Java/jdk1.8.0_361/jre/lib/rt.jar";
    }
}