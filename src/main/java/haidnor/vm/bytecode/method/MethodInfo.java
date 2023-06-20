package haidnor.vm.bytecode.method;

import haidnor.vm.bytecode.attribute.Attribute;
import haidnor.vm.bytecode.constant.ConstantUtf8Info;

import java.util.List;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.6

method_info {
    u2             access_flags;
    u2             name_index;
    u2             descriptor_index;
    u2             attributes_count;
    attribute_info attributes[attributes_count];
}

*/
public class MethodInfo {

    /**
     * access_flags 项的值是标志的掩码，用于表示对此方法的访问权限和属性。表 4.6-A 中指定了设置时每个标志的解释。
     */
    public int accessFlags;

    public MethodAccessFlagEnum accessFlagEnum;

    /**
     * The value of the name_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7) representing either one
     * of the special method names <init> or <clinit> (§2.9), or a valid unqualified name denoting a method (§4.2.2).
     */
    public int nameIndex;

    public ConstantUtf8Info nameConstantUtf8Info;

    /**
     * The value of the descriptor_index item must be a valid index into the constant_pool table.
     * The constant_pool entry at that index must be a CONSTANT_Utf8_info structure representing a valid method descriptor (§4.3.3).
     * <p>
     * A future edition of this specification may require that the last parameter descriptor of the method descriptor
     * is an array type if the ACC_VARARGS flag is set in the access_flags item.
     */
    public int descriptorIndex;

    public ConstantUtf8Info descriptorConstantUtf8Info;

    /**
     * The value of the attributes_count item indicates the number of additional attributes of this method.
     */
    public int attributesCount;

    /**
     * Each value of the attributes table must be an attribute_info structure (§4.7).
     * <p>
     * A method can have any number of optional attributes associated with it.
     * <p>
     * The attributes defined by this specification as appearing in the attributes table of a method_info structure are listed in Table 4.7-C.
     * <p>
     * The rules concerning attributes defined to appear in the attributes table of a method_info structure are given in §4.7.
     * <p>
     * The rules concerning non-predefined attributes in the attributes table of a method_info structure are given in §4.7.1.
     */
    public List<Attribute> attributes;


    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

}
