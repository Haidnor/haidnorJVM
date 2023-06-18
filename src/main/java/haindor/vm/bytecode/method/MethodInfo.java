package haindor.vm.bytecode.method;

import haindor.vm.bytecode.attribute.Attribute;

import java.util.ArrayList;
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
    public final int accessFlags;

    public final int nameIndex;

    public final int descriptorIndex;

    public final int attributesCount;

    public final List<Attribute> attributes;

    public MethodInfo(int accessFlags, int nameIndex, int descriptorIndex, int attributesCount) {
        this.accessFlags = accessFlags;
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        this.attributesCount = attributesCount;
        this.attributes = new ArrayList<>(attributesCount);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }
    
}
