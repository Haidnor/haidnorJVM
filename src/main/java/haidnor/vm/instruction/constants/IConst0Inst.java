package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

/**
 * https://docs.oracle.com/javase/specs/jvms/se19/html/jvms-6.html#jvms-6.5.iconst_i
 */
@Slf4j
public class IConst0Inst extends AbstractInstruction {

    public IConst0Inst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        log.debug("execute: IConst0");
        frame.push(new StackValue(Const.T_INT, 0));
    }

}
