package haidnor.vm.instruction;

import haidnor.vm.instruction.comparisons.IfICmpNeInst;
import haidnor.vm.instruction.constants.IConst0Inst;
import haidnor.vm.instruction.constants.IConst1Inst;
import haidnor.vm.instruction.constants.IConst2Inst;
import haidnor.vm.instruction.constants.LdcInst;
import haidnor.vm.instruction.control.ReturnInst;
import haidnor.vm.instruction.loads.ILoad1Inst;
import haidnor.vm.instruction.references.GetStaticInst;
import haidnor.vm.instruction.references.InvokeVirtualInst;
import haidnor.vm.instruction.stores.IStore1Inst;
import haidnor.vm.util.CodeStream;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

@Slf4j
public abstract class InstructionFactory {

    public static AbstractInstruction creatInstruction(CodeStream codeStream) {
        int instructionCode = codeStream.readInstructionCode();
        switch (instructionCode) {
            case Const.ICONST_0 -> {
                log.debug("{} ICONST_0", codeStream.index());
                return new IConst0Inst(codeStream);
            }
            case Const.ICONST_1 -> {
                log.debug("{} ICONST_1", codeStream.index());
                return new IConst1Inst(codeStream);
            }
            case Const.ICONST_2 -> {
                log.debug("{} ICONST_2", codeStream.index());
                return new IConst2Inst(codeStream);
            }
            case Const.ISTORE_1 -> {
                log.debug("{} ISTORE_1", codeStream.index());
                return new IStore1Inst(codeStream);
            }
            case Const.ILOAD_1 -> {
                log.debug("{} ILOAD_1", codeStream.index());
                return new ILoad1Inst(codeStream);
            }
            case Const.IF_ICMPNE -> {
                log.debug("{} IF_ICMPNE", codeStream.index());
                return new IfICmpNeInst(codeStream);
            }
            case Const.LDC -> {
                log.debug("{} LDC", codeStream.index());
                return new LdcInst(codeStream);
            }
            case Const.RETURN -> {
                log.debug("{} RETURN", codeStream.index());
                return new ReturnInst(codeStream);
            }
            case Const.GETSTATIC -> {
                log.debug("{} GETSTATIC", codeStream.index());
                return new GetStaticInst(codeStream);
            }
            case Const.INVOKEVIRTUAL -> {
                log.debug("{} INVOKEVIRTUAL", codeStream.index());
                return new InvokeVirtualInst(codeStream);
            }
            default -> throw new Error("无效指令");
        }
    }

}
