package haidnor.vm.instruction;

import haidnor.vm.instruction.comparisons.*;
import haidnor.vm.instruction.constants.*;
import haidnor.vm.instruction.control.RETURN;
import haidnor.vm.instruction.loads.ILOAD_0;
import haidnor.vm.instruction.loads.ILOAD_1;
import haidnor.vm.instruction.loads.ILOAD_2;
import haidnor.vm.instruction.loads.ILOAD_3;
import haidnor.vm.instruction.references.GETSTATIC;
import haidnor.vm.instruction.references.INVOKEVIRTUAL;
import haidnor.vm.instruction.stores.ISTORE_0;
import haidnor.vm.instruction.stores.ISTORE_1;
import haidnor.vm.instruction.stores.ISTORE_2;
import haidnor.vm.instruction.stores.ISTORE_3;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

@Slf4j
public abstract class InstructionFactory {

    public static Instruction creatInstruction(CodeStream codeStream) {
        int instructionCode = codeStream.readInstructionCode();
        switch (instructionCode) {
            case Const.ICONST_M1 -> {
                return new ICONST_M1(codeStream);
            }
            case Const.ICONST_0 -> {
                return new ICONST_0(codeStream);
            }
            case Const.ICONST_1 -> {
                return new ICONST_1(codeStream);
            }
            case Const.ICONST_2 -> {
                return new ICONST_2(codeStream);
            }
            case Const.ICONST_3 -> {
                return new ICONST_3(codeStream);
            }
            case Const.ICONST_4 -> {
                return new ICONST_4(codeStream);
            }
            case Const.ICONST_5 -> {
                return new ICONST_5(codeStream);
            }
            case Const.ISTORE_0 -> {
                return new ISTORE_0(codeStream);
            }
            case Const.ISTORE_1 -> {
                return new ISTORE_1(codeStream);
            }
            case Const.ISTORE_2 -> {
                return new ISTORE_2(codeStream);
            }
            case Const.ISTORE_3 -> {
                return new ISTORE_3(codeStream);
            }
            case Const.ILOAD_0 -> {
                return new ILOAD_0(codeStream);
            }
            case Const.ILOAD_1 -> {
                return new ILOAD_1(codeStream);
            }
            case Const.ILOAD_2 -> {
                return new ILOAD_2(codeStream);
            }
            case Const.ILOAD_3 -> {
                return new ILOAD_3(codeStream);
            }
            case Const.IF_ICMPEQ -> {
                return new IF_ICMPEQ(codeStream);
            }
            case Const.IF_ICMPGE -> {
                return new IF_ICMPGE(codeStream);
            }
            case Const.IF_ICMPGT -> {
                return new IF_ICMPGT(codeStream);
            }
            case Const.IF_ICMPLE -> {
                return new IF_ICMPLE(codeStream);
            }
            case Const.IF_ICMPLT -> {
                return new IF_ICMPLT(codeStream);
            }
            case Const.IF_ICMPNE -> {
                return new IF_ICMPNE(codeStream);
            }
            case Const.LDC -> {
                return new LDC(codeStream);
            }
            case Const.RETURN -> {
                return new RETURN(codeStream);
            }
            case Const.GETSTATIC -> {
                return new GETSTATIC(codeStream);
            }
            case Const.INVOKEVIRTUAL -> {
                return new INVOKEVIRTUAL(codeStream);
            }
            default -> throw new Error("Invalid Instruction Code " + instructionCode);
        }
    }

}
