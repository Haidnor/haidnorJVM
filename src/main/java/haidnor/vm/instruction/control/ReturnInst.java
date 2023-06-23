package haidnor.vm.instruction.control;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReturnInst extends AbstractInstruction {

    public ReturnInst(CodeStream codeStream) {
        super(codeStream);
    }

    @Override
    public void execute(Frame frame) {
        log.info("execute: RETURN");    // pop 出栈帧

        frame.getJvmThread().pop();

        log.info("jvm thread stack size:" + frame.getJvmThread().stackSize());
    }

}
