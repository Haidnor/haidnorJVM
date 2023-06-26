package haidnor.vm.runtime;

import org.apache.bcel.Const;

import java.util.Stack;

/**
 * JVM 线程栈帧
 */
public class Frame {

    /**
     * 操作数栈
     */
    private final Stack<StackValue> operandStack = new Stack<>();

    /**
     * 压入操作数栈
     */
    public void push(StackValue stackValue) {
        this.operandStack.push(stackValue);
    }

    /**
     * 弹出操作数栈顶元素
     */
    public StackValue pop() {
        return this.operandStack.pop();
    }

    /**
     * 从操作数栈中弹出指定数量的元素的值
     */
    public Object[] popObjets(int num) {
        Object[] objArr = new Object[num];
        for (int i = 0; i < num; i++) {
            StackValue stackValue = operandStack.pop();

            switch (stackValue.getType()) {
                case Const.T_CHAR:
                    objArr[i] = stackValue.getValue();
                    break;
                case Const.T_INT:
                    objArr[i] = stackValue.getValue();
                    break;
                case Const.T_OBJECT:
                    objArr[i] = stackValue.getValue();
                    break;
                case Const.T_LONG:
                    objArr[i] = stackValue.getValue();
                    break;
                case Const.T_DOUBLE:
                    objArr[i] = stackValue.getValue();
                    break;
                case Const.T_ARRAY:
                    throw new Error("数组类型，未作处理");
                default:
                    throw new Error("无法识别的参数类型");
            }
        }
        return objArr;
    }
}
