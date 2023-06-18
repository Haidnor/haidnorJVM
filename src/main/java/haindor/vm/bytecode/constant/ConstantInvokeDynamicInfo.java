package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.10

CONSTANT_InvokeDynamic_info 结构由 invokedynamic 指令 (§invokedynamic) 用于指定引导方法、动态调用名称、调用的参数和返回类型，以及可选的、称为引导方法静态参数的附加常量序列.

CONSTANT_InvokeDynamic_info {
    u1 tag;
    u2 bootstrap_method_attr_index;
    u2 name_and_type_index;
}

CONSTANT_InvokeDynamic_info 结构的 tag 项的值为 CONSTANT_InvokeDynamic (18)。
 */
public class ConstantInvokeDynamicInfo extends ConstantInfo {

    /**
     * bootstrap_method_attr_index 项的值必须是此 class 文件的引导方法表（§4.7.23）的 bootstrap_methods 数组的有效索引。
     */
    public final int bootstrapMethodAttrIndex;

    /**
     * name_and_type_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_NameAndType_info 结构（§4.4.6），表示方法名称和方法描述符（§4.3.3）。
     */
    public final int nameAndTypeIndex;

    public ConstantInvokeDynamicInfo(int bootstrapMethodAttrIndex, int nameAndTypeIndex) {
        super(ConstantInfoConstants.CONSTANT_InvokeDynamic);
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

}
