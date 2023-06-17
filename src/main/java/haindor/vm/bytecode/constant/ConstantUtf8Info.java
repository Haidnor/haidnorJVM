package haindor.vm.bytecode.constant;

/*
https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4.7

CONSTANT_Utf8_info 结构用于表示常量字符串值：

CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}

CONSTANT_Utf8_info 结构的 tag 项的值为 CONSTANT_Utf8 (1)。

 */
public class ConstantUtf8Info extends ConstantInfo {

    /**
     * length 项的值给出了 bytes 数组中的字节数（不是结果字符串的长度）。
     */
    public final int length;

    /**
     * bytes 数组包含字符串的字节。
     * 任何字节都不能具有值 (byte)0 。
     * 任何字节都不能位于 (byte)0xf0 到 (byte)0xff 的范围内。
     */
    public final byte[] bytes;

    public ConstantUtf8Info(int length, byte[] bytes) {
        super(ConstantInfoEnum.CONSTANT_Utf8.tag);
        this.length = length;
        this.bytes = bytes;
    }

}
