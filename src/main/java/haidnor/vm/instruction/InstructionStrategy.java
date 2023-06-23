package haidnor.vm.instruction;

import haidnor.vm.instruction.constants.LdcInst;
import haidnor.vm.instruction.control.ReturnInst;
import haidnor.vm.instruction.references.GetStaticInst;
import haidnor.vm.instruction.references.InvokeVirtualInst;
import org.apache.bcel.Const;

import java.util.HashMap;
import java.util.Map;

public abstract class InstructionStrategy {

    private final static Map<Short, Instruction> strategyMap = new HashMap<>();

    static {
        strategyMap.put(Const.LDC, new LdcInst());
        strategyMap.put(Const.RETURN, new ReturnInst());
        strategyMap.put(Const.GETSTATIC, new GetStaticInst());
        strategyMap.put(Const.INVOKEVIRTUAL, new InvokeVirtualInst());
    }

    public static Instruction getInstruction(Short instructionCode) {
        return strategyMap.get(instructionCode);
    }

}
