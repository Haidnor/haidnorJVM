package haidnor.vm.instruction;

import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;

public abstract class AbstractInstruction {
    /**
     * 指令坐在 code 数组中的索引下标
     */
    private final int index;

    /**
     * 执行下一个执行的偏移量
     */
    private int offSet = 1;

    public AbstractInstruction(CodeStream codeStream) {
        this.index = codeStream.index();
    }

    public abstract void execute(Frame frame);

    public int index() {
        return index;
    }

    public final int offSet() {
        return this.offSet;
    }

    public void setOffSet(int offSet) {
        this.offSet = offSet;
    }
}
