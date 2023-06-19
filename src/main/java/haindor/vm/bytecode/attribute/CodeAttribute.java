package haindor.vm.bytecode.attribute;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.3

Code_attribute {
    u2 attribute_name_index;
    u4 attribute_length;
    u2 max_stack;
    u2 max_locals;
    u4 code_length;
    u1 code[code_length];
    u2 exception_table_length;
    {   u2 start_pc;
        u2 end_pc;
        u2 handler_pc;
        u2 catch_type;
    } exception_table[exception_table_length];
    u2 attributes_count;
    attribute_info attributes[attributes_count];
}

*/
public class CodeAttribute extends Attribute {

    /**
     * max_stack 项的值给出了在该方法执行期间的任何时候该方法的操作数堆栈的最大深度（§2.6.2）。
     */
    public int maxStack;

    /**
     * max_locals 项的值给出了调用此方法时分配的局部变量数组中局部变量的数量（§2.6.1），包括用于在方法调用时将参数传递给方法的局部变量。
     * <p>
     * long 或 double 类型值的最大局部变量索引是 max_locals - 2 。任何其他类型的值的最大局部变量索引是 max_locals - 1 。
     */
    public int maxLocals;

    /**
     * code_length 项的值给出了此方法的 code 数组中的字节数。
     * code_length 的值必须大于零（因为 code 数组不能为空）且小于 65536。
     */
    public int codeLength;

    public CodeAttribute(int attributeNameIndex, int attributeLength) {
        super(attributeNameIndex, attributeLength);
    }

}
