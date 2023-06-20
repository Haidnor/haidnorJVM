package haidnor.vm.bytecode;

import haidnor.vm.bytecode.field.FieldAccessFlagConstants;

/*
field_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}
 */
public class FieldInfo {

    /**
     * access_flags 项的值是标志的掩码，用于表示对该字段的访问权限和属性。表 4.5-A 中指定了设置时每个标志的解释。
     * {@link FieldAccessFlagConstants}
     * <p>
     * 类的字段可以设置表 4.5-A 中的任何标志。但是，一个类的每个字段最多可以设置其 ACC_PUBLIC 、 ACC_PRIVATE 和 ACC_PROTECTED 标志之一（JLS §8.3.1），
     * 并且不能同时设置其 ACC_FINAL 和 ACC_VOLATILE 标志设置（JLS §8.3.1.4）。
     * <p>
     * 接口字段必须设置其 ACC_PUBLIC 、 ACC_STATIC 和 ACC_FINAL 标志；他们可以设置自己的 ACC_SYNTHETIC 标志，并且不得设置表 4.5-A 中的任何其他标志（JLS §9.3）。
     * <p>
     * ACC_SYNTHETIC 标志表示该字段由编译器生成，未出现在源代码中。
     * <p>
     * ACC_ENUM 标志表示该字段用于保存枚举类型的元素。
     * <p>
     * 表 4.5-A 中未分配的 access_flags 项的所有位都保留供将来使用。它们应该在生成的 class 文件中设置为零，并且应该被 Java 虚拟机实现忽略。
     */
    public final int accessFlags;

    /**
     * name_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），
     * 它表示表示字段的有效非限定名称（§4.2.2）。
     */
    public final int nameIndex;

    /**
     * 描述符索引
     * descriptor_index 项的值必须是 constant_pool 表中的有效索引。该索引处的 constant_pool 条目必须是 CONSTANT_Utf8_info 结构（§4.4.7），表示有效的字段描述符（§4.3.2）。
     */
    public final int descriptorIndex;

    /**
     * attributes_count 项的值表示该字段附加属性的个数。
     */
    public final int attributesCount;

    /**
     * attributes 表的每个值都必须是一个 attribute_info 结构（§4.7）。
     * <p>
     * 一个字段可以有任意数量的与之关联的可选属性。
     * <p>
     * 本规范定义的出现在 field_info 结构的 attributes 表中的属性在表 4.7-C 中列出。
     * <p>
     * 有关定义为出现在 field_info 结构的 attributes 表中的属性的规则在§4.7 中给出。
     * <p>
     * 有关 field_info 结构的 attributes 表中非预定义属性的规则在§4.7.1 中给出。
     */
    public final Attributes attributes;

    public FieldInfo(int accessFlags, int nameIndex, int descriptorIndex, int attributesCount, Attributes attributes) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = attributes;
    }
}
