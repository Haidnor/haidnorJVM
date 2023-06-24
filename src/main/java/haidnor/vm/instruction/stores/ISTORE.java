package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;

public class ISTORE extends Instruction {

    private final int index;

    public ISTORE(CodeStream codeStream) {
        super(codeStream);
        this.index = codeStream.readByteOperand(this);
    }

    @Override
    public void execute(Frame frame) {
        StackValue value = frame.pop();
        frame.slotSetInt(index, (Integer) value.getValue());
    }

}
