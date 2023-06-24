package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.lconst_l"> Opcode definitions
 *      in The Java Virtual Machine Specification</a>
 */
@Slf4j
public class LCONST_1 extends Instruction {

    public LCONST_1(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        frame.push(new StackValue(Const.T_LONG, 1));
    }

}
