package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2

CONSTANT_Methodref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}

CONSTANT_Methodref_info 结构的 tag 项的值为 CONSTANT_Methodref (10)。

*/
public class ConstantMethodrefInfo extends ConstantInfo {

    /**
     * class_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是一个 CONSTANT_Class_info 结构（§4.4.1），
     * 表示具有字段或方法作为成员的类或接口类型。
     * <p>
     * CONSTANT_Methodref_info 结构的 class_index 项必须是类类型，而不是接口类型。
     */
    public final int classIndex;

    /**
     * name_and_type_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_NameAndType_info 结构（§4.4.6）。
     * 此 constant_pool 条目指示字段或方法的名称和描述符。
     * <p>
     * 如果 CONSTANT_Methodref_info 结构的方法名称以“ < ”（“ \u003c ”）开头，则该名称必须是特殊名称 <init> ，表示实例初始化方法（§2.9）。这种方法的返回类型必须是 void 。
     */
    public final int nameAndTypeIndex;

    public ConstantMethodrefInfo(int classIndex, int nameAndTypeIndex) {
        super(ConstantInfoEnum.CONSTANT_Methodref.tag);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

}
