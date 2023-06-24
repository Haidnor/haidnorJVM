package haidnor.vm.util;

import haidnor.vm.instruction.Instruction;
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

    /**
     * 读取占用一个字节指定代码
     */
    @SneakyThrows
    public int readInstructionCode() {
        this.index += 1;
        return this.codeStream.read();
    }

    /**
     * 读取占用一个字节的操作数
     */
    @SneakyThrows
    public int readU1Operand(Instruction instruction) {
        instruction.setOffSet(instruction.offSet() + 1);
        this.index += 1;
        return this.codeStream.read();
    }

    /**
     * 读取占用两个字节的操作数
     */
    @SneakyThrows
    public int readU2Operand(Instruction instruction) {
        instruction.setOffSet(instruction.offSet() + 2);
        this.index += 2;
        return this.codeStream.readUnsignedShort();
    }

    public int index() {
        return this.index;
    }

}
