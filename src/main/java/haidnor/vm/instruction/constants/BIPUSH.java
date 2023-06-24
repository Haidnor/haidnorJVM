package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import org.apache.bcel.Const;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.bipush"> Opcode definitions in
 * The Java Virtual Machine Specification</a>
 */
public class BIPUSH extends Instruction {

    private final int value;

    public BIPUSH(CodeStream codeStream) {
        super(codeStream);
        this.value = codeStream.readByteOperand(this);
    }

    @Override
    public void execute(Frame frame) {
        frame.push(new StackValue(Const.T_INT, value));
    }

}
