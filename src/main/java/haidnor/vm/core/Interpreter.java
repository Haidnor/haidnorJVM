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
        while (codeStream.available() > 0) {
            AbstractInstruction instruction = InstructionFactory.creatInstruction(codeStream);
            instructionMap.put(instruction.index(), instruction);
        }

        for (int i = 0; i < frame.getCodeLength(); ) {
            AbstractInstruction instruction = instructionMap.get(i);
            instruction.execute(frame);
            if (instruction instanceof ReturnInst) {
                break;
            }
            i += instruction.offSet();
        }

    }

}