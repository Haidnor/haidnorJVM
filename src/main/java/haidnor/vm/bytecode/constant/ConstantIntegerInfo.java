package haidnor.vm.bytecode.constant;

/*
CONSTANT_Integer_info  结构表示 4 字节数字（ int 和 float ）常量：

https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.4

CONSTANT_Integer_info {
    u1 tag;
    u4 bytes;
}

CONSTANT_Integer_info 结构的 tag 项的值为 CONSTANT_Integer (3)。

 */
public class ConstantIntegerInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_Integer;

    /**
     * CONSTANT_Integer_info 结构的 bytes 项表示 int 常量的值。值的字节以大端（高字节在前）顺序存储。
     */
    public final int val;

    public ConstantIntegerInfo(int val) {
        super(ConstantInfoConstants.CONSTANT_Integer);
        this.val = val;
    }

    @Override
    public ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}

