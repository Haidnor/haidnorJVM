package haidnor.vm.instruction;

import haidnor.vm.runtime.Frame;

public interface Instruction {

    void execute(Frame frame);

}
