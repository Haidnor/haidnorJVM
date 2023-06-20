package haindor.vm.classloader;

public abstract class InstructionReader {

//    public static Instruction read(int opCode, MyDataInputStream stm, ConstantPool constantPool)
//            throws IOException {
//        switch (opCode) {
//            case 0x12:
//                int index = stm.readUnsignedByte();
//                ConstantInfo info = constantPool.infos[index - 1];
//                switch (info.infoEnum) {
//                    case CONSTANT_String:
//                        int stringIndex = ((StringCp) info).stringIndex;
//                        String string = Utils.getString(constantPool, stringIndex);
//                        return new LdcInst("Ljava/lang/String", string);
//                    case CONSTANT_Integer:
//                        return new LdcInst("I", ((IntegerCp) info).val);
//                    case CONSTANT_Float:
//                        return new LdcInst("F", ((FloatCp) info).val);
//                    case CONSTANT_Class:
//                        return new LdcInst("L", Utils.getString(constantPool, ((ClassCp) info).nameIndex));
//                }
//                throw new IllegalStateException();
//            case 0xb1:
//                return new ReturnInst();
//            case 0xb2:
//                int gsIndex = stm.readUnsignedShort();
//                return new GetStaticInst(
//                        Utils.getClassNameByFieldDefIdx(constantPool, gsIndex),
//                        Utils.getMethodNameByFieldDefIdx(constantPool, gsIndex),
//                        Utils.getMethodTypeByFieldDefIdx(constantPool, gsIndex)
//                );
//            default:
//                throw new UnsupportedOperationException("unknown op code");
//                return null;
//        }
//    }
}
