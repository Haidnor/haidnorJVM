package haidnor.vm.instruction.constants;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import lombok.SneakyThrows;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.Constant;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.ConstantString;

/**
 * Java VM opcode.
 *
 * @see <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-6.html#jvms-6.5.ldc"> Opcode definitions in The
 *      Java Virtual Machine Specification</a>
 */
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
            default:
                throw new Error("not supported LDC type" + constant.getTag());
        }
    }

}
