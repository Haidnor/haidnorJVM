package haidnor.jvm.util;

import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

public abstract class JavaClassUtil {

    /**
     * 获取 main 方法
     */
    public static Method getMainMethod(JavaClass javaClass) {
        for (Method method : javaClass.getMethods()) {
            if (method.toString().equals("public static void main(String[] args)")) {
                return method;
            }
        }
        return null;
    }

}
