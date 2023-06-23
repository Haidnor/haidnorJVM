package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public class IStore2Inst extends AbstractInstruction {

    public IStore2Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {

    }
    
}
