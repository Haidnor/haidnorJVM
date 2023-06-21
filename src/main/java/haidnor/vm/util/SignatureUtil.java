package haidnor.vm.util;

import lombok.SneakyThrows;
import org.apache.bcel.classfile.Utility;

public abstract class SignatureUtil {

    /**
     * 解析方法签名返回方法参数类型数组
     */
    @SneakyThrows
    public static Class<?>[] getParameterTypes(String methodeSignature) {
        String[] argumentTypeArr = Utility.methodSignatureArgumentTypes(methodeSignature, false);
        Class<?>[] argumentClassArr = new Class[argumentTypeArr.length];
        for (int i = 0; i < argumentTypeArr.length; i++) {
            Class<?> argumentClass = Class.forName(argumentTypeArr[i]);
            argumentClassArr[i] = argumentClass;
        }
        return argumentClassArr;
    }

}
