package haidnor.vm.util;

import lombok.SneakyThrows;
import org.apache.bcel.classfile.Code;

import java.io.DataInputStream;

public class CodeStream {

    /**
     * 当前读取到的数组下标
     * -1 代表还没有开始读
     */
    private int index = -1;

    public Code code;

    private final DataInputStream codeStream;

    public CodeStream(Code code) {
        this.code = code;
        this.codeStream = IoUtil.getDataInputStream(code.getCode());
    }

    @SneakyThrows
    public int available() {
        return codeStream.available();
    }

    @SneakyThrows
    public int readU1() {
        this.index += 1;
        return this.codeStream.read();
    }

    @SneakyThrows
    public int readU2() {
        this.index += 2;
        return this.codeStream.readUnsignedShort();
    }

    public int index() {
        return this.index;
    }

}
