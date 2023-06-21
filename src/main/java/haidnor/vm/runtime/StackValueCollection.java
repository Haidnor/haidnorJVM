package haidnor.vm.runtime;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Stack;

@Data
@Slf4j
public class StackValueCollection {

    /*************************************
     * 模拟操作数栈
     */
    private Stack<StackValue> container = new Stack<>();

    public StackValueCollection() {
    }

    public void push(StackValue value) {
        getContainer().push(value);
    }

    public StackValue pop() {
        return getContainer().pop();
    }

    public StackValue peek() {
        return getContainer().peek();
    }

    /****************************************
     *
     *  模拟局部变量表
     */
    private int maxLocals;
    private int index;
    private StackValue[] locals;

    public StackValueCollection(int size) {
        maxLocals = size;

        locals = new StackValue[size];
    }

    public void add(int index, StackValue value) {
        getLocals()[index] = value;
    }

    public StackValue get(int index) {
        return getLocals()[index];
    }

}
