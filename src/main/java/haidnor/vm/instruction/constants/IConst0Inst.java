package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public class IConst0Inst extends AbstractInstruction {

    public IConst0Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {

    }
}
