package haindor.vm.bytecode.constant;

public abstract class ConstantInfo {

    public final int tag;

    public ConstantInfo(int tag) {
        this.tag = tag;
    }

}
