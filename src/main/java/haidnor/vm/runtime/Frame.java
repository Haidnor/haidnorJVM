package haidnor.vm.runtime;

import haidnor.vm.util.CodeStream;
import haidnor.vm.util.ConstantPoolUtil;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Method;

import java.util.Stack;

/**
 * JVM 线程栈帧
 */
public class Frame {
    /**
     * 当前栈帧所处于的 JVM 线程
     */
    private final JvmThread jvmThread;

    /**
     * 栈帧所属的方法
     */
    private final Method method;

    /**
     * 栈帧所属的方法代码对象
     */
    private final Code code;

    private final CodeStream codeStream;

    private final ConstantPool constantPool;

    private final ConstantPoolUtil constantPoolUtil;

    /**
     * 操作数栈
     */
    private final Stack<StackValue> operandStack = new Stack<>();

    /**
     * 局部变量表
     */
    private final LocalVariableTable localVariableTable;

    /**
     * 槽位
     */
    private final Slot[] slots;

    public Frame(JvmThread jvmThread, Method method) {
        this.jvmThread = jvmThread;
        this.method = method;
        this.code = method.getCode();
        this.constantPool = method.getConstantPool();
        this.constantPoolUtil = new ConstantPoolUtil(constantPool);
        this.codeStream = new CodeStream(method.getCode());
        this.localVariableTable = code.getLocalVariableTable();
        this.slots = new Slot[code.getMaxLocals()];
    }

    public JvmThread getJvmThread() {
        return jvmThread;
    }

    public int getCodeLength() {
        return this.code.getCode().length;
    }

    public CodeStream getCodeStream() {
        return codeStream;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public ConstantPoolUtil getConstantPoolUtil() {
        return constantPoolUtil;
    }

    /* 操作数栈操作 --------------------------------------------------------------------------------------------------- */

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

    /* 局部变量表操作 --------------------------------------------------------------------------------------------------- */

    public void slotSetInt(int index, int val) {
        slots[index] = new Slot(val);
    }

    public int slotGetInt(int index) {
        return slots[index].num;
    }

    public void slotSetFloat(int index, float val) {
        int tmp = Float.floatToIntBits(val);
        slots[index] = new Slot(tmp);
    }

    public float slotGetFloat(int index) {
        int num = slots[index].num;
        return Float.intBitsToFloat(num);
    }

    public long slotGetLong(int index) {
        int high = slots[index].num;
        int low = slots[index + 1].num;

        long l1 = (high & 0x000000ffffffffL) << 32;
        long l2 = low & 0x00000000ffffffffL;
        return l1 | l2;
    }

    public void slotSetLong(int index, long val) {
        // high 32
        int high = (int) (val >> 32);
        // low 32
        int low = (int) (val & 0x000000ffffffffL);

        slots[index] = new Slot(high);
        slots[index + 1] = new Slot(low);
    }

    public void slotSetDouble(int index, double val) {
        long tmp = Double.doubleToLongBits(val);
        // high 32
        int high = (int) (tmp >> 32);
        // low 32
        int low = (int) (tmp & 0x000000ffffffffL);

        slots[index] = new Slot(high);
        slots[index + 1] = new Slot(low);
    }

    public double slotGetDouble(int index) {
        long tmp = this.slotGetLong(index);
        return Double.longBitsToDouble(tmp);
    }

    public void slotSetRef(int index, Instance ref) {
        slots[index] = new Slot(ref);
    }

    public Instance slotGetRef(int index) {
        return slots[index].ref;
    }

}
