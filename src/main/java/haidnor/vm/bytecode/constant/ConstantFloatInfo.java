package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.4

CONSTANT_Float_info 结构表示 4 字节数字 float 常量：

CONSTANT_Float_info {
    u1 tag;
    u4 bytes;
}

CONSTANT_Float_info 结构的 tag 项的值为 CONSTANT_Float (4)。
 */
public class ConstantFloatInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_Float;

    /**
     * CONSTANT_Float_info 结构的 bytes 项表示 IEEE 754 浮点单一格式（§2.3.2）中的 float 常量的值。单一格式表示的字节以大端（高字节在前）顺序存储。
     * <p>
     * CONSTANT_Float_info 结构表示的值是这样确定的。值的字节首先被转换为 int 常量位。然后：
     * 如果位为 0x7f800000 ，则 float 值将为正无穷大。
     * 如果位为 0xff800000 ，则 float 值将为负无穷大。
     * 如果位在 0x7f800001 到 0x7fffffff 范围内或 0xff800001 到 0xffffffff 范围内，则 float 值将为NaN。
     * 在所有其他情况下，让 s 、 e 和 m 是可以从位计算的三个值：
     * int s = ((bits >> 31) == 0) ? 1 : -1;
     * int e = ((bits >> 23) & 0xff);
     * int m = (e == 0) ?
     * (bits & 0x7fffff) << 1 :
     * (bits & 0x7fffff) | 0x800000;
     * 然后 float 值等于数学表达式 s · m · 2e-150 的结果。
     */
    public final float val;

    public ConstantFloatInfo(float val) {
        super(ConstantInfoConstants.CONSTANT_Float);
        this.val = val;
    }

    @Override
    public ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}
