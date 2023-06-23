package haidnor.vm.core;

import haidnor.vm.instruction.InstructionStrategy;
import haidnor.vm.instruction.constants.LdcInst;
import haidnor.vm.instruction.control.ReturnInst;
import haidnor.vm.instruction.references.GetStaticInst;
import haidnor.vm.instruction.references.InvokeVirtualInst;
import haidnor.vm.runtime.Frame;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;

import java.io.DataInputStream;

@Slf4j
public class Interpreter {

    @SneakyThrows
    public static void executeFrame(Frame frame) {
        DataInputStream codeStream = frame.getCodeStream();
        while (codeStream.available() > 0) {
            switch (codeStream.read()) {
                case Const.LDC -> {
                    LdcInst instruction = (LdcInst) InstructionStrategy.getInstruction(Const.LDC);
                    instruction.execute(frame);
                }
                case Const.RETURN -> {
                    ReturnInst instruction = (ReturnInst) InstructionStrategy.getInstruction(Const.RETURN);
                    instruction.execute(frame);
                }
                case Const.GETSTATIC -> {
                    GetStaticInst instruction = (GetStaticInst) InstructionStrategy.getInstruction(Const.GETSTATIC);
                    instruction.execute(frame);
                }
                case Const.INVOKEVIRTUAL -> {
                    InvokeVirtualInst instruction = (InvokeVirtualInst) InstructionStrategy.getInstruction(Const.INVOKEVIRTUAL);
                    instruction.execute(frame);
                }
                default -> throw new Error("无效指令");
            }
        }
    }

}