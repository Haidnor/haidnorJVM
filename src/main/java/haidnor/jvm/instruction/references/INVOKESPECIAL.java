package haidnor.jvm.instruction.references;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.util.CodeStream;
import lombok.SneakyThrows;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;

public class INVOKESPECIAL extends Instruction {

    private final int constantMethodrefIndex;

    public INVOKESPECIAL(CodeStream codeStream) {
        super(codeStream);
        this.constantMethodrefIndex = codeStream.readUnsignedShort(this);
    }

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getConstantPool();
        ConstantMethodref methodref = constantPool.getConstant(constantMethodrefIndex);
        // TODO wangxiang
    }

}
