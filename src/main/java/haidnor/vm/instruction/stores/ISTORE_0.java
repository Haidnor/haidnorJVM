package haidnor.vm.instruction.stores;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ISTORE_0 extends Instruction {

    public ISTORE_0(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        log.debug("IStore1");
        StackValue value = frame.pop();
        frame.slotSetInt(0, (Integer) value.getValue());
    }

}
