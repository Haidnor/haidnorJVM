package haindor.vm.bytecode.constant;

import cn.hutool.core.util.HexUtil;

import java.nio.charset.StandardCharsets;

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

    public final String constantInfoName = ConstantInfoEnum.CONSTANT_Utf8.name();

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

    /**
     * 字符串字面量
     * 将读取到 16 进制数据转解码后得到的字符串
     */
    public final String utf8Str;

    public ConstantUtf8Info(int length, byte[] bytes) {
        super(ConstantInfoConstants.CONSTANT_Utf8);
        this.length = length;
        this.bytes = bytes;
        this.utf8Str = HexUtil.decodeHexStr(HexUtil.encodeHex(bytes), StandardCharsets.UTF_8);
    }

}
