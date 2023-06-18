package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.3

CONSTANT_String_info 结构用于表示 String 类型的常量对象：

CONSTANT_String_info {
    u1 tag;
    u2 string_index;
}

CONSTANT_String_info 结构的 tag 项的值为 CONSTANT_String (8)。

 */
public class ConstantStringInfo extends ConstantInfo {

    /**
     * string_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），表示 String 对象要初始化到的 Unicode 代码点序列。
     */
    public final int stringIndex;

    public ConstantStringInfo(int stringIndex) {
        super(ConstantInfoConstants.CONSTANT_String);
        this.stringIndex = stringIndex;
    }

}
