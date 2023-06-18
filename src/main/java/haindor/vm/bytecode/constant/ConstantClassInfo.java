package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.1

CONSTANT_Class_info 结构用于表示一个类或者一个接口

CONSTANT_Class_info {
    u1 tag;
    u2 name_index;
}

The tag item has the value CONSTANT_Class (7).
*/
public class ConstantClassInfo extends ConstantInfo {

    public final String constantInfoName = ConstantInfoEnum.CONSTANT_Class.name();

    /**
     * 名称索引
     * name_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），表示以内部形式（§4.2.1）编码的有效二进制类或接口名称。
     */
    public final int nameIndex;

    public ConstantUtf8Info nameConstantUtf8Info;

    public ConstantClassInfo(int nameIndex) {
        super(ConstantInfoConstants.CONSTANT_Class);
        this.nameIndex = nameIndex;
    }

    public void setNameConstantUtf8Info(ConstantUtf8Info nameConstantUtf8Info) {
        this.nameConstantUtf8Info = nameConstantUtf8Info;
    }

}