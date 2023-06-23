package haidnor.vm.instruction.comparisons;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IfICmpNeInst extends AbstractInstruction {
    /**
     * 下次再执行的偏移量
     */
    private final int operand;

    public IfICmpNeInst(CodeStream codeStream) {
        super(codeStream);
        this.operand = codeStream.readU2Operand(this);
    }

    @Override
    public void execute(Frame frame) {
        log.debug("execute: IfICmpNe");
        StackValue v1 = frame.pop();
        StackValue v2 = frame.pop();
        if (!v1.getValue().equals(v2.getValue())) {
            super.setOffSet(operand);
        }
    }

}
