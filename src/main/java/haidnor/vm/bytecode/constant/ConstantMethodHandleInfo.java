package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.9

CONSTANT_MethodHandle_info 结构用于表示一个方法句柄：

CONSTANT_MethodHandle_info {
    u1 tag;
    u1 reference_kind;
    u2 reference_index;
}

CONSTANT_MethodHandle_info 结构的 tag 项的值为 CONSTANT_MethodHandle (15)。
 */
public class ConstantMethodHandleInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_MethodHandle;

    /**
     * reference_kind 项的值必须在 1 到 9 的范围内。该值表示此方法句柄的种类，它表征其字节码行为（§5.4.3.5）。
     */
    public final int referenceKind;

    /**
     * reference_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须如下所示：
     * <p>
     * 如果 reference_kind 项的值为 1 ( REF_getField )、2 ( REF_getStatic )、3 ( REF_putField ) 或 4 ( REF_putStatic )，
     * 则该索引处的 constant_pool 条目必须是 CONSTANT_Fieldref_info （§4.4.2）结构表示要为其创建方法句柄的字段。
     * <p>
     * 如果 reference_kind 项的值为 5 ( REF_invokeVirtual ) 或 8 ( REF_newInvokeSpecial )，
     * 则该索引处的 constant_pool 条目必须是表示类方法的 CONSTANT_Methodref_info 结构（§4.4.2）或要为其创建方法句柄的构造函数（§2.9）。
     * <p>
     * 如果 reference_kind 项的值为 6 ( REF_invokeStatic ) 或 7 ( REF_invokeSpecial )，则如果 class 文件版本号小于 52.0，
     * 则该索引处的 constant_pool 条目必须是 @ 5# 表示要为其创建方法句柄的类方法的结构；如果 class 文件版本号为 52.0 或更高，
     * 则该索引处的 constant_pool 条目必须是 CONSTANT_Methodref_info 结构或 CONSTANT_InterfaceMethodref_info 结构（§4.4.2），表示类或接口的方法要创建方法句柄。
     * <p>
     * 如果 reference_kind 项的值为 9 ( REF_invokeInterface )，则该索引处的 constant_pool 条目必须是 CONSTANT_InterfaceMethodref_info 结构，表示要为其创建方法句柄的接口方法。
     * <p>
     * 如果 reference_kind 项的值为 5 ( REF_invokeVirtual )、6 ( REF_invokeStatic )、7 ( REF_invokeSpecial ) 或 9 ( REF_invokeInterface )，
     * 则由 CONSTANT_Methodref_info 表示的方法名称结构或 CONSTANT_InterfaceMethodref_info 结构不得为 <init> 或 <clinit> 。
     * <p>
     * 如果值为 8（ REF_newInvokeSpecial ），则由 CONSTANT_Methodref_info 结构表示的方法名称必须为 <init> 。
     */
    public final int referenceIndex;

    public ConstantMethodHandleInfo(int referenceKind, int referenceIndex) {
        super(ConstantInfoConstants.CONSTANT_MethodHandle);
        this.referenceKind = referenceKind;
        this.referenceIndex = referenceIndex;
    }

    @Override
    public  ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}
