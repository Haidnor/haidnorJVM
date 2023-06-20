package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.9

CONSTANT_MethodType_info 结构用于表示一个方法类型：

CONSTANT_MethodType_info {
    u1 tag;
    u2 descriptor_index;
}

CONSTANT_MethodType_info 结构的 tag 项的值为 CONSTANT_MethodType (16)。

 */
public class ConstantMethodtypeInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_MethodType;

    /**
     * descriptor_index 描述符索引
     * <p>
     * descriptor_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是表示方法描述符（§4.3.3）的 CONSTANT_Utf8_info 结构（§4.4.7）。
     */
    public final int descriptorIndex;

    public ConstantUtf8Info descriptorConstantUtf8Info;

    public ConstantMethodtypeInfo(int descriptorIndex) {
        super(ConstantInfoConstants.CONSTANT_MethodType);
        this.descriptorIndex = descriptorIndex;
    }

    public void setDescriptorConstantUtf8Info(ConstantUtf8Info descriptorConstantUtf8Info) {
        this.descriptorConstantUtf8Info = descriptorConstantUtf8Info;
    }

    @Override
    public  ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}

