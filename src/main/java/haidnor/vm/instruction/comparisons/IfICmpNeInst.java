package haidnor.vm.instruction.comparisons;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public class IfICmpNeInst extends AbstractInstruction {

    private final int operand;

    public IfICmpNeInst(CodeStream codeStream) {
        super(codeStream);
        this.operand = codeStream.readU2();
    }

    @Override
    public void execute(Frame frame) {

    }

}
