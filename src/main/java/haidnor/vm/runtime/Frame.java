package haidnor.vm.runtime;

import haidnor.vm.util.ConstantPoolUtil;
import haidnor.vm.util.IoUtil;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Method;

import java.io.DataInputStream;
import java.util.Stack;

/**
 * JVM 线程栈帧
 */
public class Frame {

    private final JvmThread jvmThread;

    private final Method method;

    private final DataInputStream codeStream;

    private final ConstantPool constantPool;

    private final ConstantPoolUtil constantPoolUtil;

    /**
     * 操作数栈
     */
    private final Stack<StackValue> operandStack = new Stack<>();

    public Frame(JvmThread jvmThread, Method method) {
        this.jvmThread = jvmThread;
        this.method = method;
        this.constantPool = method.getConstantPool();
        this.constantPoolUtil = new ConstantPoolUtil(constantPool);
        this.codeStream = IoUtil.getDataInputStream(method.getCode().getCode());
    }

    public JvmThread getJvmThread() {
        return jvmThread;
    }

    public Method getMethod() {
        return method;
    }

    public DataInputStream getCodeStream() {
        return codeStream;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ConstantPoolUtil getConstantPoolUtil() {
        return constantPoolUtil;
    }

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
    public Object[] popStacksValue(int num) {
        Object[] objArr = new Object[num];
        for (int i = 0; i < num; i++) {
            StackValue stackValue = operandStack.pop();
            switch (stackValue.getType()) {
                case Const.T_CHAR, Const.T_INT, Const.T_OBJECT, Const.T_LONG, Const.T_DOUBLE:
                    break;
                case Const.T_ARRAY:
                    throw new Error("数组类型，未作处理");
                default:
                    throw new Error("无法识别的参数类型");
            }
            objArr[i] = stackValue.getValue();
        }
        return objArr;
    }

}
