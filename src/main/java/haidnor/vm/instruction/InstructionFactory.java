package haidnor.vm.instruction;

import haidnor.vm.instruction.comparisons.*;
import haidnor.vm.instruction.constants.*;
import haidnor.vm.instruction.control.Return;
import haidnor.vm.instruction.loads.ILoad0;
import haidnor.vm.instruction.loads.ILoad1;
import haidnor.vm.instruction.loads.ILoad2;
import haidnor.vm.instruction.loads.ILoad3;
import haidnor.vm.instruction.references.GetStatic;
import haidnor.vm.instruction.references.InvokeVirtual;
import haidnor.vm.instruction.stores.IStore0;
import haidnor.vm.instruction.stores.IStore1;
import haidnor.vm.instruction.stores.IStore2;
import haidnor.vm.instruction.stores.IStore3;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

@Slf4j
public abstract class InstructionFactory {

    public static Instruction creatInstruction(CodeStream codeStream) {
        int instructionCode = codeStream.readInstructionCode();
        switch (instructionCode) {
            case Const.ICONST_M1 -> {
                return new IConstM1(codeStream);
            }
            case Const.ICONST_0 -> {
                return new IConst0(codeStream);
            }
            case Const.ICONST_1 -> {
                return new IConst1(codeStream);
            }
            case Const.ICONST_2 -> {
                return new IConst2(codeStream);
            }
            case Const.ICONST_3 -> {
                return new IConst3(codeStream);
            }
            case Const.ICONST_4 -> {
                return new IConst4(codeStream);
            }
            case Const.ICONST_5 -> {
                return new IConst5(codeStream);
            }
            case Const.ISTORE_0 -> {
                return new IStore0(codeStream);
            }
            case Const.ISTORE_1 -> {
                return new IStore1(codeStream);
            }
            case Const.ISTORE_2 -> {
                return new IStore2(codeStream);
            }
            case Const.ISTORE_3 -> {
                return new IStore3(codeStream);
            }
            case Const.ILOAD_0 -> {
                return new ILoad0(codeStream);
            }
            case Const.ILOAD_1 -> {
                return new ILoad1(codeStream);
            }
            case Const.ILOAD_2 -> {
                return new ILoad2(codeStream);
            }
            case Const.ILOAD_3 -> {
                return new ILoad3(codeStream);
            }
            case Const.IF_ICMPEQ -> {
                return new IfICmpEq(codeStream);
            }
            case Const.IF_ICMPGE -> {
                return new IfICmpGe(codeStream);
            }
            case Const.IF_ICMPGT -> {
                return new IfICmpGt(codeStream);
            }
            case Const.IF_ICMPLE -> {
                return new IfICmpLe(codeStream);
            }
            case Const.IF_ICMPLT -> {
                return new IfICmpLt(codeStream);
            }
            case Const.IF_ICMPNE -> {
                return new IfICmpNe(codeStream);
            }
            case Const.LDC -> {
                return new Ldc(codeStream);
            }
            case Const.RETURN -> {
                return new Return(codeStream);
            }
            case Const.GETSTATIC -> {
                return new GetStatic(codeStream);
            }
            case Const.INVOKEVIRTUAL -> {
                return new InvokeVirtual(codeStream);
            }
            default -> throw new Error("Invalid Instruction Code " + instructionCode);
        }
    }

}
