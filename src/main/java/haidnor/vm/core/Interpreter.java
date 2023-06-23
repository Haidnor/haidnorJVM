package haidnor.vm.core;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.instruction.InstructionFactory;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;
import haidnor.vm.util.CodeUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.Code;

import java.util.*;

@Slf4j
public class Interpreter {

    @SneakyThrows
    public static void executeFrame(Frame frame) {
        CodeStream codeStream = frame.getCodeStream();

        Code code = frame.getCode();
        Set<Integer> startPcSet = CodeUtil.getStartPcSet(code);

        // K:startPC V:Instruction
        Map<Integer, List<AbstractInstruction>> instructionMap = new LinkedHashMap<>();

        List<AbstractInstruction> instructionList = null;
        while (codeStream.available() > 0) {
            int instructionCode = codeStream.readU1();
            AbstractInstruction instruction = InstructionFactory.creatInstruction(instructionCode, codeStream);

            int index = instruction.index();
            if (startPcSet.contains(instruction.index()) && instructionMap.get(instruction.index()) == null) {
                instructionList = new ArrayList<>();
                instructionMap.put(index, instructionList);
            }

            assert instructionList != null;
            instructionList.add(instruction);
        }

        for (Integer startPc : instructionMap.keySet()) {
            System.out.println(startPc);
        }
    }

}