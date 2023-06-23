package haidnor.vm.instruction;

import haidnor.vm.instruction.comparisons.IfICmpNeInst;
import haidnor.vm.instruction.constants.IConst0Inst;
import haidnor.vm.instruction.constants.IConst1Inst;
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

    public static AbstractInstruction creatInstruction(int instructionCode, CodeStream codeStream) {
        switch (instructionCode) {
            case Const.ICONST_0 -> {
                log.info("{} ICONST_0", codeStream.index());
                return new IConst0Inst(codeStream);
            }
            case Const.ICONST_1 -> {
                log.info("{} ICONST_1", codeStream.index());
                return new IConst1Inst(codeStream);
            }
            case Const.ISTORE_1 -> {
                log.info("{} ISTORE_1", codeStream.index());
                return new IStore1Inst(codeStream);
            }
            case Const.ILOAD_1 -> {
                log.info("{} ILOAD_1", codeStream.index());
                return new ILoad1Inst(codeStream);
            }
            case Const.IF_ICMPNE -> {
                log.info("{} IF_ICMPNE", codeStream.index());
                return new IfICmpNeInst(codeStream);
            }
            case Const.LDC -> {
                log.info("{} LDC", codeStream.index());
                return new LdcInst(codeStream);
            }
            case Const.RETURN -> {
                log.info("{} RETURN", codeStream.index());
                return new ReturnInst(codeStream);
            }
            case Const.GETSTATIC -> {
                log.info("{} GETSTATIC", codeStream.index());
                return new GetStaticInst(codeStream);
            }
            case Const.INVOKEVIRTUAL -> {
                log.info("{} INVOKEVIRTUAL", codeStream.index());
                return new InvokeVirtualInst(codeStream);
            }
            default -> throw new Error("无效指令");
        }
    }

}
