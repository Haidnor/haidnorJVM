package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;

public class ISTORE_2 extends Instruction {

    public ISTORE_2(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        StackValue value = frame.pop();
        frame.slotSetInt(2, (Integer) value.getValue());
    }
    
}
