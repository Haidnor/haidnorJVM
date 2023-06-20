package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.6

CONSTANT_NameAndType_info 结构用于表示一个字段或方法，不指明它属于哪个类或接口类型：

CONSTANT_NameAndType_info {
    u1 tag;
    u2 name_index;
    u2 descriptor_index;
}

CONSTANT_NameAndType_info 结构的 tag 项的值为 CONSTANT_NameAndType (12)。
 */
public class ConstantNameAndTypeInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_NameAndType;

    /**
     * name_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），
     * 表示特殊方法名称 <init> （§2.9）或表示字段或方法的有效非限定名称（§4.2.2） .
     */
    public final int nameIndex;

    public ConstantUtf8Info nameConstantUtf8Info;

    /**
     * descriptor_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），表示有效的字段描述符或方法描述符（§4.3.2、§4.3.3）。
     */
    public final int descriptionIndex;

    public ConstantUtf8Info descriptionConstantUtf8Info;

    public ConstantNameAndTypeInfo(int nameIndex, int descriptionIndex) {
        super(ConstantInfoConstants.CONSTANT_NameAndType);
        this.nameIndex = nameIndex;
        this.descriptionIndex = descriptionIndex;
    }

    public void setNameConstantUtf8Info(ConstantUtf8Info nameConstantUtf8Info) {
        this.nameConstantUtf8Info = nameConstantUtf8Info;
    }

    public void setDescriptionConstantUtf8Info(ConstantUtf8Info descriptionConstantUtf8Info) {
        this.descriptionConstantUtf8Info = descriptionConstantUtf8Info;
    }

    @Override
    public   ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}

