package haidnor.vm.instruction.loads;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

@Slf4j
public class ILoad1Inst extends AbstractInstruction {

    public ILoad1Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        log.debug("execute: ILoad1");
        int value = frame.slotGetInt(1);
        frame.push(new StackValue(Const.T_INT, value));
    }

}
