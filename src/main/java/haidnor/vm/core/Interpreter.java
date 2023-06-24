package haidnor.vm.core;

import haidnor.vm.instruction.Instruction;
import haidnor.vm.instruction.InstructionFactory;
import haidnor.vm.instruction.control.RETURN;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Interpreter {

    @SneakyThrows
    public static void executeFrame(Frame frame) {
        Map<Integer, Instruction> instructionMap = new HashMap<>();

        CodeStream codeStream = frame.getCodeStream();
        log.debug("PARSE INST START ------------------------------");
        while (codeStream.available() > 0) {
            Instruction instruction = InstructionFactory.creatInstruction(codeStream);
            log.debug("{}", instruction);
            instructionMap.put(instruction.index(), instruction);
        }
        log.debug("PARSE INST END ------------------------------");

        log.debug("EXECUTE INST START ------------------------------");
        for (int i = 0; i < frame.getCodeLength(); ) {
            Instruction instruction = instructionMap.get(i);
            log.debug("{}", instruction);
            instruction.execute(frame);
            if (instruction instanceof RETURN) {
                break;
            }
            i += instruction.offSet();
        }
        log.debug("EXECUTE INST END ------------------------------");

    }

}