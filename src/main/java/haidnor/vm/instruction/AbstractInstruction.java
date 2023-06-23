package haidnor.vm.instruction;

import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public abstract class AbstractInstruction {

    private final int index;

    public AbstractInstruction(CodeStream codeStream) {
        this.index = codeStream.index();
    }

    public abstract void execute(Frame frame);

    public int index() {
        return index;
    }

}
