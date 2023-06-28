package haidnor.jvm.util;

import haidnor.jvm.rtda.heap.MetaClass;
import haidnor.jvm.rtda.heap.MetaMethod;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.classfile.Method;

public abstract class JavaClassUtil {

    /**
     * 获取 main 方法
     */
    public static MetaMethod getMainMethod(MetaClass metaClass) {
        JavaClass javaClass = metaClass.getJavaClass();
        for (Method method : javaClass.getMethods()) {
            if (method.toString().equals("public static void main(String[] args)")) {
                return new MetaMethod(metaClass, method);
            }
        }
        return null;
    }

}
