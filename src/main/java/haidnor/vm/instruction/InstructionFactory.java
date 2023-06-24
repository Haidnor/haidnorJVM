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

@SuppressWarnings("DuplicateBranchesInSwitch")
@Slf4j
public abstract class InstructionFactory {

    public static Instruction creatInstruction(CodeStream codeStream) {
        int opcode = codeStream.readJavaVmOpcode();
        switch (opcode) {
            case Const.NOP -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ACONST_NULL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
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
            case Const.LCONST_0 -> {
                return new LCONST_0(codeStream);
            }
            case Const.LCONST_1 -> {
                return new LCONST_1(codeStream);
            }
            case Const.FCONST_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FCONST_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DCONST_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DCONST_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.BIPUSH -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.SIPUSH -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LDC -> {
                return new LDC(codeStream);
            }
            case Const.LDC_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);

            }
            case Const.LDC2_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ILOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LLOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FLOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DLOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
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
            case Const.LLOAD_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LLOAD_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LLOAD_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LLOAD_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FLOAD_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FLOAD_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FLOAD_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FLOAD_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DLOAD_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DLOAD_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DLOAD_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DLOAD_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ALOAD_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ALOAD_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ALOAD_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ALOAD_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.AALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.BALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.CALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.SALOAD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ISTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
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
            case Const.LSTORE_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSTORE_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSTORE_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSTORE_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSTORE_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSTORE_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSTORE_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSTORE_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSTORE_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSTORE_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSTORE_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSTORE_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ASTORE_0 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ASTORE_1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ASTORE_2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ASTORE_3 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.AASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.BASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.CASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.SASTORE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.POP -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.POP2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP_X1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP_X2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP2_X1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DUP2_X2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.SWAP -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IADD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LADD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FADD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DADD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ISUB -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSUB -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FSUB -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DSUB -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IMUL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LMUL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FMUL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DMUL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IDIV -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LDIV -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FDIV -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DDIV -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IREM -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LREM -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FREM -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DREM -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INEG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LNEG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FNEG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DNEG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ISHL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSHL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ISHR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LSHR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IUSHR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LUSHR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IAND -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LAND -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IOR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LOR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IXOR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LXOR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IINC -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.I2L -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.I2F -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.I2D -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.L2I -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.L2F -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.L2D -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.F2I -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.F2L -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.F2D -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.D2I -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.D2L -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.D2F -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.I2B -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
//            case Const.INT2BYTE -> {
//                throw new Error("Not support JavaVM opcode " + opcode);
//            }
            case Const.I2C -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
//            case Const.INT2CHAR -> {
//                throw new Error("Not support JavaVM opcode " + opcode);
//            }
            case Const.I2S -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
//            case Const.INT2SHORT -> {
//                throw new Error("Not support JavaVM opcode " + opcode);
//            }
            case Const.LCMP -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FCMPL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FCMPG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DCMPL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DCMPG -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFEQ -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFNE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFLT -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFGE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFGT -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFLE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IF_ICMPEQ -> {
                return new IF_ICMPEQ(codeStream);
            }
            case Const.IF_ICMPNE -> {
                return new IF_ICMPNE(codeStream);
            }
            case Const.IF_ICMPLT -> {
                return new IF_ICMPLT(codeStream);
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
            case Const.IF_ACMPEQ -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IF_ACMPNE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GOTO -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.JSR -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.RET -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.TABLESWITCH -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LOOKUPSWITCH -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IRETURN -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LRETURN -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.FRETURN -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.DRETURN -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ARETURN -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.RETURN -> {
                return new RETURN(codeStream);
            }
            case Const.GETSTATIC -> {
                return new GETSTATIC(codeStream);
            }
            case Const.PUTSTATIC -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETFIELD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTFIELD -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEVIRTUAL -> {
                return new INVOKEVIRTUAL(codeStream);
            }
            case Const.INVOKESPECIAL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
//            case Const.INVOKENONVIRTUAL -> {
//                throw new Error("Not support JavaVM opcode " + opcode);
//            }
            case Const.INVOKESTATIC -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEINTERFACE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEDYNAMIC -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.NEW -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.NEWARRAY -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ANEWARRAY -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ARRAYLENGTH -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ATHROW -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.CHECKCAST -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INSTANCEOF -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.MONITORENTER -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.MONITOREXIT -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.WIDE -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.MULTIANEWARRAY -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFNULL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IFNONNULL -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GOTO_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.JSR_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.BREAKPOINT -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LDC_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LDC_W_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.LDC2_W_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETFIELD_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTFIELD_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETFIELD2_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTFIELD2_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETSTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTSTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETSTATIC2_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTSTATIC2_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEVIRTUAL_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKENONVIRTUAL_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKESUPER_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKESTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEINTERFACE_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEVIRTUALOBJECT_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.NEW_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.ANEWARRAY_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.MULTIANEWARRAY_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.CHECKCAST_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INSTANCEOF_QUICK -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.INVOKEVIRTUAL_QUICK_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.GETFIELD_QUICK_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.PUTFIELD_QUICK_W -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IMPDEP1 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            case Const.IMPDEP2 -> {
                throw new Error("Not support JavaVM opcode " + opcode);
            }
            default -> throw new Error("Unknown JavaVM opcode " + opcode);
        }
    }

}
