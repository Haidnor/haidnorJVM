package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantString;

@Slf4j
public class LDC extends Instruction {

    private final int constantIndex;

    public LDC(CodeStream codeStream) {
        super(codeStream);
        this.constantIndex = codeStream.readU1Operand(this);
    }

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getConstantPool();

        // 从常量池中获取值
        Constant constant = constantPool.getConstant(constantIndex);

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
