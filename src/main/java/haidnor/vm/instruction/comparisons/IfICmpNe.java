package haidnor.vm.instruction.comparisons;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.if_icmp_cond"> Opcode
 * definitions in The Java Virtual Machine Specification</a>
 */
@Slf4j
public class IfICmpNe extends Instruction {
    /**
     * 下次再执行的偏移量
     */
    private final int operand;

    public IfICmpNe(CodeStream codeStream) {
        super(codeStream);
        this.operand = codeStream.readU2Operand(this);
    }

    @Override
    public void execute(Frame frame) {
        StackValue v1 = frame.pop();
        StackValue v2 = frame.pop();

        if ((int) v1.getValue() != (int) v2.getValue()) {
            super.setOffSet(operand);
        }
    }

    @Override
    public String toString() {
        return super.index() + " " + this.getClass().getSimpleName() + " "  + operand;
    }

}
