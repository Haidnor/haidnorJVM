package haidnor.vm.instruction.comparisons;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
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
        this.operand = codeStream.readU2();
    }

    @Override
    public void execute(Frame frame) {
        log.info("execute: IfICmpNe");
    }

    @Override
    public int nextOffSet() {
        return operand;
    }

}
