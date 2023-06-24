package haidnor.vm.instruction.loads;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import org.apache.bcel.Const;


public class ILOAD extends Instruction {

    private final int index;

    public ILOAD(CodeStream codeStream) {
        super(codeStream);
        this.index = codeStream.readByteOperand(this);
    }

    @Override
    public void execute(Frame frame) {
        int value = frame.slotGetInt(index);
        frame.push(new StackValue(Const.T_INT, value));
    }

}
