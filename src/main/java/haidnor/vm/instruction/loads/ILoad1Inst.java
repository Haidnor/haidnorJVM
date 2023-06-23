package haidnor.vm.instruction.loads;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public class ILoad1Inst extends AbstractInstruction {

    public ILoad1Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {

    }
}
