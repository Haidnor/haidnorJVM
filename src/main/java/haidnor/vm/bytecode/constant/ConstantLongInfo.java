package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5

CONSTANT_Long_info 表示 8 字节数字 long 常量：

CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}

CONSTANT_NameAndType_info 结构的 tag 项的值为 CONSTANT_NameAndType (12)。
 */
public class ConstantLongInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_Long;

    public final long val;

    public ConstantLongInfo(long val) {
        super(ConstantInfoConstants.CONSTANT_Long);
        this.val = val;
    }

    @Override
    public ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}
