package haidnor.vm.bytecode.attribute;

import haidnor.vm.bytecode.constant.ConstantUtf8Info;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.10

*/
public class SourceFileAttribute extends Attribute {

    /**
     * sourcefile_index 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是表示字符串的 CONSTANT_Utf8_info 结构（§4.4.7）。
     * <p>
     * sourcefile_index 项引用的字符串将被解释为指示从中编译此 class 文件的源文件的名称。
     * 它不会被解释为指示包含文件的目录的名称或文件的绝对路径名；
     * 此类特定于平台的附加信息必须在实际使用文件名时由运行时解释器或开发工具提供。
     */
    public int sourcefileIndex;

    public ConstantUtf8Info sourcefileConstantUtf8Info;

    public SourceFileAttribute(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

}
