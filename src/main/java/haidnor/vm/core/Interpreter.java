package haidnor.vm.core;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.instruction.InstructionFactory;
import haidnor.vm.instruction.control.ReturnInst;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class Interpreter {

    @SneakyThrows
    public static void executeFrame(Frame frame) {
        Map<Integer, AbstractInstruction> instructionMap = new LinkedHashMap<>();

        CodeStream codeStream = frame.getCodeStream();

        // 最后一个指令
        int lastInstructionIndex = -1;
        while (codeStream.available() > 0) {
            int instructionCode = codeStream.readU1();
            AbstractInstruction instruction = InstructionFactory.creatInstruction(instructionCode, codeStream);
            instructionMap.put(instruction.index(), instruction);
            if (instruction.index() > lastInstructionIndex) {
                lastInstructionIndex = instruction.index();
            }
        }

        for (int i = 0; i <= lastInstructionIndex; ) {
            AbstractInstruction abstractInstruction = instructionMap.get(i);
            abstractInstruction.execute(frame);
            if (abstractInstruction instanceof ReturnInst) {
                break;
            }
            int offSet = abstractInstruction.nextOffSet();
            i += offSet;
        }

    }

}