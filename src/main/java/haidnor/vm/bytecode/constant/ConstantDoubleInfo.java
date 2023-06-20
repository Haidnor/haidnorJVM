package haidnor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.5

CONSTANT_Double_info 表示 8 字节数字 double 常量：

CONSTANT_Long_info {
    u1 tag;
    u4 high_bytes;
    u4 low_bytes;
}

CONSTANT_Double_info 结构的 tag 项的值为 CONSTANT_Double (6)。
 */
public class ConstantDoubleInfo extends ConstantInfo {

    public final ConstantInfoEnum constantInfoEnum = ConstantInfoEnum.CONSTANT_Double;

    public final double val;

    public ConstantDoubleInfo(double val) {
        super(ConstantInfoConstants.CONSTANT_Double);
        this.val = val;
    }

    @Override
    public   ConstantInfoEnum getConstantInfoEnum() {
        return constantInfoEnum;
    }
}
