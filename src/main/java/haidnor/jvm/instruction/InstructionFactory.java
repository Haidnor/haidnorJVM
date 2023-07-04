package haidnor.jvm.instruction;

import haidnor.jvm.instruction.comparisons.*;
import haidnor.jvm.instruction.constants.*;
import haidnor.jvm.instruction.control.*;
import haidnor.jvm.instruction.conversions.*;
import haidnor.jvm.instruction.extended.GOTO;
import haidnor.jvm.instruction.loads.*;
import haidnor.jvm.instruction.math.*;
import haidnor.jvm.instruction.references.*;
import haidnor.jvm.instruction.stack.DUP;
import haidnor.jvm.instruction.stack.POP;
import haidnor.jvm.instruction.stack.POP2;
import haidnor.jvm.instruction.stores.*;
import haidnor.jvm.util.CodeStream;
import org.apache.bcel.Const;

public abstract class InstructionFactory {

    public static Instruction creatInstruction(CodeStream codeStream) {
        int opcode = codeStream.readJavaVmOpcode();
        switch (opcode) {
            case Const.NOP -> {
                return new NOP(codeStream);
            }
            case Const.ACONST_NULL -> {
                return new ACONST_NULL(codeStream);
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
                return new FCONST_1(codeStream);
            }
            case Const.FCONST_2 -> {
                return new FCONST_2(codeStream);
            }
            case Const.DCONST_0 -> {
                return new DCONST_0(codeStream);
            }
            case Const.DCONST_1 -> {
                return new DCONST_1(codeStream);
            }
            case Const.BIPUSH -> {
                return new BIPUSH(codeStream);
            }
            case Const.SIPUSH -> {
                return new SIPUSH(codeStream);
            }
            case Const.LDC -> {
                return new LDC(codeStream);
            }
            case Const.LDC_W -> {
                return new LDC_W(codeStream);
            }
            case Const.LDC2_W -> {
                return new LDC2W(codeStream);
            }
            case Const.ILOAD -> {
                return new ILOAD(codeStream);
            }
            case Const.LLOAD -> {
                return new LLOAD(codeStream);
            }
            case Const.FLOAD -> {
                return new FLOAD(codeStream);
            }
            case Const.DLOAD -> {
                return new DLOAD(codeStream);
            }
            case Const.ALOAD -> {
                return new ALOAD(codeStream);
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
                return new LLOAD_0(codeStream);
            }
            case Const.LLOAD_1 -> {
                return new LLOAD_1(codeStream);
            }
            case Const.LLOAD_2 -> {
                return new LLOAD_2(codeStream);
            }
            case Const.LLOAD_3 -> {
                return new LLOAD_3(codeStream);
            }
            case Const.FLOAD_0 -> {
                return new FLOAD_0(codeStream);
            }
            case Const.FLOAD_1 -> {
                return new FLOAD_1(codeStream);
            }
            case Const.FLOAD_2 -> {
                return new FLOAD_2(codeStream);
            }
            case Const.FLOAD_3 -> {
                return new FLOAD_3(codeStream);
            }
            case Const.DLOAD_0 -> {
                return new DLOAD_0(codeStream);
            }
            case Const.DLOAD_1 -> {
                return new DLOAD_1(codeStream);
            }
            case Const.DLOAD_2 -> {
                return new DLOAD_2(codeStream);
            }
            case Const.DLOAD_3 -> {
                return new DLOAD_3(codeStream);
            }
            case Const.ALOAD_0 -> {
                return new ALOAD_0(codeStream);
            }
            case Const.ALOAD_1 -> {
                return new ALOAD_1(codeStream);
            }
            case Const.ALOAD_2 -> {
                return new ALOAD_2(codeStream);
            }
            case Const.ALOAD_3 -> {
                return new ALOAD_3(codeStream);
            }
            case Const.IALOAD -> {
                throw new Error("Not support JavaVM opcode IALOAD");
            }
            case Const.LALOAD -> {
                throw new Error("Not support JavaVM opcode LALOAD");
            }
            case Const.FALOAD -> {
                throw new Error("Not support JavaVM opcode FALOAD");
            }
            case Const.DALOAD -> {
                throw new Error("Not support JavaVM opcode DALOAD");
            }
            case Const.AALOAD -> {
                throw new Error("Not support JavaVM opcode AALOAD");
            }
            case Const.BALOAD -> {
                throw new Error("Not support JavaVM opcode BALOAD");
            }
            case Const.CALOAD -> {
                throw new Error("Not support JavaVM opcode CALOAD");
            }
            case Const.SALOAD -> {
                throw new Error("Not support JavaVM opcode SALOAD");
            }
            case Const.ISTORE -> {
                return new ISTORE(codeStream);
            }
            case Const.LSTORE -> {
                return new LSTORE(codeStream);
            }
            case Const.FSTORE -> {
                return new FSTORE(codeStream);
            }
            case Const.DSTORE -> {
                return new DSTORE(codeStream);
            }
            case Const.ASTORE -> {
                return new ASTORE(codeStream);
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
                return new LSTORE_0(codeStream);
            }
            case Const.LSTORE_1 -> {
                return new LSTORE_1(codeStream);
            }
            case Const.LSTORE_2 -> {
                return new LSTORE_2(codeStream);
            }
            case Const.LSTORE_3 -> {
                return new LSTORE_3(codeStream);
            }
            case Const.FSTORE_0 -> {
                return new FSTORE_0(codeStream);
            }
            case Const.FSTORE_1 -> {
                return new FSTORE_1(codeStream);
            }
            case Const.FSTORE_2 -> {
                return new FSTORE_2(codeStream);
            }
            case Const.FSTORE_3 -> {
                return new FSTORE_3(codeStream);
            }
            case Const.DSTORE_0 -> {
                return new DSTORE_0(codeStream);
            }
            case Const.DSTORE_1 -> {
                return new DSTORE_1(codeStream);
            }
            case Const.DSTORE_2 -> {
                return new DSTORE_2(codeStream);
            }
            case Const.DSTORE_3 -> {
                return new DSTORE_3(codeStream);
            }
            case Const.ASTORE_0 -> {
                return new ASTORE_0(codeStream);
            }
            case Const.ASTORE_1 -> {
                return new ASTORE_1(codeStream);
            }
            case Const.ASTORE_2 -> {
                return new ASTORE_2(codeStream);
            }
            case Const.ASTORE_3 -> {
                return new ASTORE_3(codeStream);
            }
            case Const.IASTORE -> {
                throw new Error("Not support JavaVM opcode IASTORE");
            }
            case Const.LASTORE -> {
                throw new Error("Not support JavaVM opcode LASTORE");
            }
            case Const.FASTORE -> {
                throw new Error("Not support JavaVM opcode FASTORE");
            }
            case Const.DASTORE -> {
                throw new Error("Not support JavaVM opcode DASTORE");
            }
            case Const.AASTORE -> {
                throw new Error("Not support JavaVM opcode AASTORE");
            }
            case Const.BASTORE -> {
                throw new Error("Not support JavaVM opcode BASTORE");
            }
            case Const.CASTORE -> {
                throw new Error("Not support JavaVM opcode CASTORE");
            }
            case Const.SASTORE -> {
                throw new Error("Not support JavaVM opcode SASTORE");
            }
            case Const.POP -> {
                return new POP(codeStream);
            }
            case Const.POP2 -> {
                return new POP2(codeStream);
            }
            case Const.DUP -> {
                return new DUP(codeStream);
            }
            case Const.DUP_X1 -> {
                throw new Error("Not support JavaVM opcode DUP_X1");
            }
            case Const.DUP_X2 -> {
                throw new Error("Not support JavaVM opcode DUP_X2");
            }
            case Const.DUP2 -> {
                throw new Error("Not support JavaVM opcode DUP2");
            }
            case Const.DUP2_X1 -> {
                throw new Error("Not support JavaVM opcode DUP2_X1");
            }
            case Const.DUP2_X2 -> {
                throw new Error("Not support JavaVM opcode DUP2_X2");
            }
            case Const.SWAP -> {
                throw new Error("Not support JavaVM opcode SWAP");
            }
            case Const.IADD -> {
                return new IADD(codeStream);
            }
            case Const.LADD -> {
                return new LADD(codeStream);
            }
            case Const.FADD -> {
                return new FADD(codeStream);
            }
            case Const.DADD -> {
                return new DADD(codeStream);
            }
            case Const.ISUB -> {
                return new ISUB(codeStream);
            }
            case Const.LSUB -> {
                return new LSUB(codeStream);
            }
            case Const.FSUB -> {
                return new FSUB(codeStream);
            }
            case Const.DSUB -> {
                return new DSUB(codeStream);
            }
            case Const.IMUL -> {
                return new IMUL(codeStream);
            }
            case Const.LMUL -> {
                return new LMUL(codeStream);
            }
            case Const.FMUL -> {
                return new FMUL(codeStream);
            }
            case Const.DMUL -> {
                return new DMUL(codeStream);
            }
            case Const.IDIV -> {
                return new IDIV(codeStream);
            }
            case Const.LDIV -> {
                return new LDIV(codeStream);
            }
            case Const.FDIV -> {
                return new FDIV(codeStream);
            }
            case Const.DDIV -> {
                return new DDIV(codeStream);
            }
            case Const.IREM -> {
                return new IREM(codeStream);
            }
            case Const.LREM -> {
                return new LREM(codeStream);
            }
            case Const.FREM -> {
                return new FREM(codeStream);
            }
            case Const.DREM -> {
                return new DREM(codeStream);
            }
            case Const.INEG -> {
                return new INEG(codeStream);
            }
            case Const.LNEG -> {
                return new LNEG(codeStream);
            }
            case Const.FNEG -> {
                return new FNEG(codeStream);
            }
            case Const.DNEG -> {
                return new DNEG(codeStream);
            }
            case Const.ISHL -> {
                return new ISHL(codeStream);
            }
            case Const.LSHL -> {
                return new LSHL(codeStream);
            }
            case Const.ISHR -> {
                return new ISHR(codeStream);
            }
            case Const.LSHR -> {
                return new LSHR(codeStream);
            }
            case Const.IUSHR -> {
                return new IUSHR(codeStream);
            }
            case Const.LUSHR -> {
                return new LUSHR(codeStream);
            }
            case Const.IAND -> {
                return new IAND(codeStream);
            }
            case Const.LAND -> {
                return new LAND(codeStream);
            }
            case Const.IOR -> {
                return new IOR(codeStream);
            }
            case Const.LOR -> {
                return new LOR(codeStream);
            }
            case Const.IXOR -> {
                return new IXOR(codeStream);
            }
            case Const.LXOR -> {
                return new LXOR(codeStream);
            }
            case Const.IINC -> {
                return new IINC(codeStream);
            }
            case Const.I2L -> {
                return new I2L(codeStream);
            }
            case Const.I2F -> {
                return new I2F(codeStream);
            }
            case Const.I2D -> {
                return new I2D(codeStream);
            }
            case Const.L2I -> {
                return new L2I(codeStream);
            }
            case Const.L2F -> {
                return new L2F(codeStream);
            }
            case Const.L2D -> {
                return new L2D(codeStream);
            }
            case Const.F2I -> {
                return new F2I(codeStream);
            }
            case Const.F2L -> {
                return new F2L(codeStream);
            }
            case Const.F2D -> {
                return new F2D(codeStream);
            }
            case Const.D2I -> {
                return new D2I(codeStream);
            }
            case Const.D2L -> {
                return new D2L(codeStream);
            }
            case Const.D2F -> {
                return new D2F(codeStream);
            }
            case Const.I2B -> {
                return new I2B(codeStream);
            }
            // Const.INT2BYTE
            case Const.I2C -> {
                return new I2C(codeStream);
            }
            // Const.INT2CHAR
            case Const.I2S -> {
                return new I2S(codeStream);
            }
            // Const.INT2SHORT
            case Const.LCMP -> {
                throw new Error("Not support JavaVM opcode LCMP");
            }
            case Const.FCMPL -> {
                throw new Error("Not support JavaVM opcode FCMPL");
            }
            case Const.FCMPG -> {
                throw new Error("Not support JavaVM opcode FCMPG");
            }
            case Const.DCMPL -> {
                throw new Error("Not support JavaVM opcode DCMPL");
            }
            case Const.DCMPG -> {
                throw new Error("Not support JavaVM opcode DCMPG");
            }
            case Const.IFEQ -> {
                throw new Error("Not support JavaVM opcode IFEQ");
            }
            case Const.IFNE -> {
                throw new Error("Not support JavaVM opcode IFNE");
            }
            case Const.IFLT -> {
                throw new Error("Not support JavaVM opcode IFLT");
            }
            case Const.IFGE -> {
                throw new Error("Not support JavaVM opcode IFGE");
            }
            case Const.IFGT -> {
                throw new Error("Not support JavaVM opcode IFGT");
            }
            case Const.IFLE -> {
                throw new Error("Not support JavaVM opcode IFLE");
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
                throw new Error("Not support JavaVM opcode IF_ACMPEQ");
            }
            case Const.IF_ACMPNE -> {
                throw new Error("Not support JavaVM opcode IF_ACMPNE");
            }
            case Const.GOTO -> {
                return new GOTO(codeStream);
            }
            case Const.JSR -> {
                throw new Error("Not support JavaVM opcode JSR");
            }
            case Const.RET -> {
                throw new Error("Not support JavaVM opcode RET");
            }
            case Const.TABLESWITCH -> {
                throw new Error("Not support JavaVM opcode TABLESWITCH");
            }
            case Const.LOOKUPSWITCH -> {
                throw new Error("Not support JavaVM opcode LOOKUPSWITCH");
            }
            case Const.IRETURN -> {
                return new IRETURN(codeStream);
            }
            case Const.LRETURN -> {
                return new LRETURN(codeStream);
            }
            case Const.FRETURN -> {
                return new FRETURN(codeStream);
            }
            case Const.DRETURN -> {
                return new DRETURN(codeStream);
            }
            case Const.ARETURN -> {
                throw new Error("Not support JavaVM opcode ARETURN");
            }
            case Const.RETURN -> {
                return new RETURN(codeStream);
            }
            case Const.GETSTATIC -> {
                return new GETSTATIC(codeStream);
            }
            case Const.PUTSTATIC -> {
                throw new Error("Not support JavaVM opcode PUTSTATIC");
            }
            case Const.GETFIELD -> {
                return new GETFIELD(codeStream);
            }
            case Const.PUTFIELD -> {
                return new PUTFIELD(codeStream);
            }
            case Const.INVOKEVIRTUAL -> {
                return new INVOKEVIRTUAL(codeStream);
            }
            case Const.INVOKESPECIAL -> {
                return new INVOKESPECIAL(codeStream);
            }
            // Const.INVOKENONVIRTUAL   Old name in JDK 1.0
            case Const.INVOKESTATIC -> {
                return new INVOKESTATIC(codeStream);
            }
            case Const.INVOKEINTERFACE -> {
                throw new Error("Not support JavaVM opcode INVOKEINTERFACE");
            }
            case Const.INVOKEDYNAMIC -> {
                throw new Error("Not support JavaVM opcode INVOKEDYNAMIC");
            }
            case Const.NEW -> {
                return new NEW(codeStream);
            }
            case Const.NEWARRAY -> {
                throw new Error("Not support JavaVM opcode NEWARRAY");
            }
            case Const.ANEWARRAY -> {
                throw new Error("Not support JavaVM opcode ANEWARRAY");
            }
            case Const.ARRAYLENGTH -> {
                throw new Error("Not support JavaVM opcode ARRAYLENGTH");
            }
            case Const.ATHROW -> {
                throw new Error("Not support JavaVM opcode ATHROW");
            }
            case Const.CHECKCAST -> {
                throw new Error("Not support JavaVM opcode CHECKCAST");
            }
            case Const.INSTANCEOF -> {
                throw new Error("Not support JavaVM opcode INSTANCEOF");
            }
            case Const.MONITORENTER -> {
                throw new Error("Not support JavaVM opcode MONITORENTER");
            }
            case Const.MONITOREXIT -> {
                throw new Error("Not support JavaVM opcode MONITOREXIT");
            }
            case Const.WIDE -> {
                throw new Error("Not support JavaVM opcode WIDE");
            }
            case Const.MULTIANEWARRAY -> {
                throw new Error("Not support JavaVM opcode MULTIANEWARRAY");
            }
            case Const.IFNULL -> {
                throw new Error("Not support JavaVM opcode IFNULL");
            }
            case Const.IFNONNULL -> {
                throw new Error("Not support JavaVM opcode IFNONNULL");
            }
            case Const.GOTO_W -> {
                throw new Error("Not support JavaVM opcode GOTO_W");
            }
            case Const.JSR_W -> {
                throw new Error("Not support JavaVM opcode JSR_W");
            }
            case Const.BREAKPOINT -> {
                throw new Error("Not support JavaVM opcode BREAKPOINT");
            }
            case Const.LDC_QUICK -> {
                throw new Error("Not support JavaVM opcode LDC_QUICK");
            }
            case Const.LDC_W_QUICK -> {
                throw new Error("Not support JavaVM opcode LDC_W_QUICK");
            }
            case Const.LDC2_W_QUICK -> {
                throw new Error("Not support JavaVM opcode LDC2_W_QUICK");
            }
            case Const.GETFIELD_QUICK -> {
                throw new Error("Not support JavaVM opcode GETFIELD_QUICK");
            }
            case Const.PUTFIELD_QUICK -> {
                throw new Error("Not support JavaVM opcode PUTFIELD_QUICK");
            }
            case Const.GETFIELD2_QUICK -> {
                throw new Error("Not support JavaVM opcode GETFIELD2_QUICK");
            }
            case Const.PUTFIELD2_QUICK -> {
                throw new Error("Not support JavaVM opcode PUTFIELD2_QUICK");
            }
            case Const.GETSTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode GETSTATIC_QUICK");
            }
            case Const.PUTSTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode PUTSTATIC_QUICK");
            }
            case Const.GETSTATIC2_QUICK -> {
                throw new Error("Not support JavaVM opcode GETSTATIC2_QUICK");
            }
            case Const.PUTSTATIC2_QUICK -> {
                throw new Error("Not support JavaVM opcode PUTSTATIC2_QUICK");
            }
            case Const.INVOKEVIRTUAL_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKEVIRTUAL_QUICK");
            }
            case Const.INVOKENONVIRTUAL_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKENONVIRTUAL_QUICK");
            }
            case Const.INVOKESUPER_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKESUPER_QUICK");
            }
            case Const.INVOKESTATIC_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKESTATIC_QUICK");
            }
            case Const.INVOKEINTERFACE_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKEINTERFACE_QUICK");
            }
            case Const.INVOKEVIRTUALOBJECT_QUICK -> {
                throw new Error("Not support JavaVM opcode INVOKEVIRTUALOBJECT_QUICK");
            }
            case Const.NEW_QUICK -> {
                throw new Error("Not support JavaVM opcode NEW_QUICK");
            }
            case Const.ANEWARRAY_QUICK -> {
                throw new Error("Not support JavaVM opcode ANEWARRAY_QUICK");
            }
            case Const.MULTIANEWARRAY_QUICK -> {
                throw new Error("Not support JavaVM opcode MULTIANEWARRAY_QUICK");
            }
            case Const.CHECKCAST_QUICK -> {
                throw new Error("Not support JavaVM opcode CHECKCAST_QUICK");
            }
            case Const.INSTANCEOF_QUICK -> {
                throw new Error("Not support JavaVM opcode INSTANCEOF_QUICK");
            }
            case Const.INVOKEVIRTUAL_QUICK_W -> {
                throw new Error("Not support JavaVM opcode INVOKEVIRTUAL_QUICK_W");
            }
            case Const.GETFIELD_QUICK_W -> {
                throw new Error("Not support JavaVM opcode GETFIELD_QUICK_W");
            }
            case Const.PUTFIELD_QUICK_W -> {
                throw new Error("Not support JavaVM opcode PUTFIELD_QUICK_W");
            }
            case Const.IMPDEP1 -> {
                throw new Error("Not support JavaVM opcode IMPDEP1");
            }
            case Const.IMPDEP2 -> {
                throw new Error("Not support JavaVM opcode IMPDEP2");
            }
            default -> throw new Error("Unknown JavaVM opcode ");
        }
    }

}
