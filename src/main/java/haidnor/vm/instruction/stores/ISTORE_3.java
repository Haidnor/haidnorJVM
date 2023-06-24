package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;

public class ISTORE_3 extends Instruction {

    public ISTORE_3(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        StackValue value = frame.pop();
        frame.slotSetInt(3, (Integer) value.getValue());
    }

}
