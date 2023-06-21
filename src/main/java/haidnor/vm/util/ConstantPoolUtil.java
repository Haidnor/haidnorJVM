package haidnor.vm.util;

import org.apache.bcel.classfile.*;

/**
 * ConstantPoolUtil
 */
public class ConstantPoolUtil {

    private final ConstantPool cp;

    public ConstantPoolUtil(ConstantPool cp) {
        this.cp = cp;
    }

    /**
     * 获取字段所处于Java类的类名
     */
    public String getBelongClassName(final ConstantFieldref constantFieldref) {
        ConstantClass constClass = cp.getConstant(constantFieldref.getClassIndex());
        return (String) constClass.getConstantValue(cp);
    }

    /**
     * 获取方法所处于Java类的类名
     */
    public String getFieldName(final ConstantFieldref constantFieldref) {
        ConstantNameAndType constNameAndType = cp.getConstant(constantFieldref.getNameAndTypeIndex());
        return constNameAndType.getName(cp);
    }

    /**
     * 获取方法所处于Java类的类名
     */
    public String getBelongClassName(final ConstantMethodref methodref) {
        ConstantClass constClass = cp.getConstant(methodref.getClassIndex());
        return (String) constClass.getConstantValue(cp);
    }

    /**
     * 获取方法名
     */
    public String getMethodName(final ConstantMethodref methodref) {
        ConstantNameAndType constNameAndType = cp.getConstant(methodref.getNameAndTypeIndex());
        return constNameAndType.getName(cp);
    }

    /**
     * 获取方法签名
     */
    public String getMethodSignature(final ConstantMethodref methodref) {
        ConstantNameAndType constNameAndType = cp.getConstant(methodref.getNameAndTypeIndex());
        return constNameAndType.getSignature(cp);
    }

}
