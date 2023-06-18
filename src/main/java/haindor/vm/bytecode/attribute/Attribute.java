package haindor.vm.bytecode.attribute;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7

attribute_info {
    u2 attribute_name_index;
    u4 attribute_length;
    u1 info[attribute_length];
}

五个属性对于 Java 虚拟机正确解释 class 文件至关重要：
ConstantValue
Code
StackMapTable
Exceptions
BootstrapMethods

十二个属性对于 Java SE 平台的类库正确解释 class 文件至关重要：
InnerClasses
EnclosingMethod
Synthetic
Signature
RuntimeVisibleAnnotations
RuntimeInvisibleAnnotations
RuntimeVisibleParameterAnnotations
RuntimeInvisibleParameterAnnotations
RuntimeVisibleTypeAnnotations
RuntimeInvisibleTypeAnnotations
AnnotationDefault
MethodParameters

六个属性对于 Java 虚拟机或 Java SE 平台的类库正确解释 class 文件并不重要，但对工具很有用：
SourceFile
SourceDebugExtension
LineNumberTable
LocalVariableTable
LocalVariableTypeTable
Deprecated
*/
public abstract class Attribute {

    /**
     * attribute_name_index 必须是类常量池中的有效无符号 16 位索引。
     * attribute_name_index 处的 constant_pool 条目必须是表示属性名称的 CONSTANT_Utf8_info 结构（§4.4.7）
     */
    public final int attributeNameIndex;

    /**
     * attribute_length 项的值表示后续信息的字节长度。该长度不包括包含 attribute_name_index 和 attribute_length 项的前六个字节。
     */
    public final int attributeLength;

    public Attribute(int attributeNameIndex, int attributeLength) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }

}
