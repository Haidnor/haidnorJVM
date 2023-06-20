package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2

CONSTANT_InterfaceMethodref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}

CONSTANT_InterfaceMethodref_info 结构的 tag 项的值为 CONSTANT_InterfaceMethodref (11)
 */
public class ConstantInterfaceMethodRefInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_InterfaceMethodref;

    /**
     * class_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是一个 CONSTANT_Class_info 结构（§4.4.1），
     * 表示具有字段或方法作为成员的类或接口类型。
     * <p>
     * CONSTANT_InterfaceMethodref_info 结构的 class_index 项必须是接口类型。
     */
    public final int classIndex;

    /**
     * name_and_type_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_NameAndType_info 结构（§4.4.6）。
     * 此 constant_pool 条目指示字段或方法的名称和描述符。
     */
    public final int nameAndTypeIndex;

    public ConstantInterfaceMethodRefInfo(int classIndex, int nameAndTypeIndex) {
        super(ConstantInfoConstants.CONSTANT_InterfaceMethodref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}
