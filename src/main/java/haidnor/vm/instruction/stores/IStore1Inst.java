package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IStore1Inst extends AbstractInstruction {

    public IStore1Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        log.debug("execute: IStore1");
        StackValue value = frame.pop();
        frame.slotSetInt(1, (Integer) value.getValue());
    }
    
}
