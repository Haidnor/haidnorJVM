package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.2

CONSTANT_Fieldref_info {
    u1 tag;
    u2 class_index;
    u2 name_and_type_index;
}

CONSTANT_Fieldref_info 结构的 tag 项的值为 CONSTANT_Fieldref (9)。

*/
public class ConstantFieldrefInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_Fieldref;

    /**
     * class_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是一个 CONSTANT_Class_info 结构（§4.4.1），
     * 表示具有字段或方法作为成员的类或接口类型。
     * <p>
     * CONSTANT_Fieldref_info 结构的 class_index 项可以是类类型或接口类型。
     */
    public final int classIndex;

    public ConstantClassInfo constantClassInfo;


    /**
     * name_and_type_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是 CONSTANT_NameAndType_info 结构（§4.4.6）。
     * 此 constant_pool 条目指示字段或方法的名称和描述符。
     * <p>
     * 在 CONSTANT_Fieldref_info 中，指示的描述符必须是字段描述符（§4.3.2）。否则，指示的描述符必须是方法描述符（§4.3.3）。
     */
    public final int nameAndTypeIndex;

    public ConstantNameAndTypeInfo constantNameAndTypeInfo;

    public ConstantFieldrefInfo(int classIndex, int nameAndTypeIndex) {
        super(ConstantInfoConstants.CONSTANT_Fieldref);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public void setConstantClassInfo(ConstantClassInfo constantClassInfo) {
        this.constantClassInfo = constantClassInfo;
    }

    public void setConstantNameAndTypeInfo(ConstantNameAndTypeInfo constantNameAndTypeInfo) {
        this.constantNameAndTypeInfo = constantNameAndTypeInfo;
    }

    @Override
    public  ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}