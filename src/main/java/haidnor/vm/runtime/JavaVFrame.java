package haidnor.vm.runtime;

import haidnor.vm.bcel.classfile.MethodInfo;
import lombok.Data;

@Data
public class JavaVFrame extends VFrame {

    private StackValueCollection locals;

    private StackValueCollection stack = new StackValueCollection(); // 操作数栈

    private MethodInfo ownerMethod;

    public JavaVFrame(int maxLocals) {
        locals = new StackValueCollection(maxLocals);
    }

    public JavaVFrame(int maxLocals, MethodInfo methodInfo) {
        locals = new StackValueCollection(maxLocals);

        ownerMethod = methodInfo;
    }
}
