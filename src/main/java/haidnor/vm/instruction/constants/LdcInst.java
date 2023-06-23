package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantString;

@Slf4j
public class LdcInst implements Instruction {

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        log.info("execute: LDC");   // 将值压入操作数栈
        ConstantPool constantPool = frame.getConstantPool();

        // 从常量池中获取值
        int operand = frame.getCodeStream().read();
        Constant constant = constantPool.getConstant(operand);

        switch (constant.getTag()) {
            case Const.CONSTANT_String: {
                ConstantString constString = (ConstantString) constant;
                Object value = constString.getConstantValue(constantPool);
                frame.push(new StackValue(Const.T_OBJECT, value));
                break;
            }
        }
    }

}
