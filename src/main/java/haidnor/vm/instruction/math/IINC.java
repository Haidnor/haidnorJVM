package haidnor.vm.instruction.math;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.iinc"> Opcode definitions in
 * The Java Virtual Machine Specification</a>
 */
public class IINC extends Instruction {

    /**
     * 局部变量表中需要被自增元素的索引
     */
    public final int index;

    /**
     * 自增值
     */
    public final int increment;

    public IINC(CodeStream codeStream) {
        super(codeStream);
        this.index = codeStream.readU1Operand(this);
        this.increment = codeStream.readU1Operand(this);
    }

    @Override
    public void execute(Frame frame) {
        int value = frame.slotGetInt(index);
        frame.slotSetInt(index, value + increment);
    }

}
