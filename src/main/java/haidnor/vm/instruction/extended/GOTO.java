package haidnor.vm.instruction.extended;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.goto"> Opcode definitions in
 *      The Java Virtual Machine Specification</a>
 */
public class GOTO extends Instruction {
    /**
     * 下次再执行的偏移量
     */
    private final int offSet;

    public GOTO(CodeStream codeStream) {
        super(codeStream);
        this.offSet = codeStream.readU2Operand(this);
    }

    @Override
    public void execute(Frame frame) {
        super.setOffSet(offSet);
    }

    @Override
    public String toString() {
        return super.index() + " " + this.getClass().getSimpleName() + " "  + offSet;
    }
}
