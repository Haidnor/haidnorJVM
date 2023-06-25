package haidnor.jvm.test;

public class BaseTest {

    public static String getJavaClassAbsolutePath(Class<?> clazz) {
        return clazz.getResource(clazz.getSimpleName() + ".class").getFile();
    }

}
