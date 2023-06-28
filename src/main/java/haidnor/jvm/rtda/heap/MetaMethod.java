package haidnor.jvm.rtda.heap;

import org.apache.bcel.classfile.Method;

public class MetaMethod {

    public final MetaClass metaClass;

    public final Method method;

    public MetaMethod(MetaClass metaClass, Method method) {
        this.metaClass = metaClass;
        this.method = method;
    }

}
