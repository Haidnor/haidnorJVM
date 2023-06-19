package haindor.vm.classloader;

import haindor.vm.bytecode.*;
import haindor.vm.bytecode.attribute.AttributeConstants;
import haindor.vm.bytecode.attribute.SourceFileAttribute;
import haindor.vm.bytecode.constant.*;
import haindor.vm.bytecode.method.MethodAccessFlagEnum;
import haindor.vm.bytecode.method.MethodInfo;
import haindor.vm.bytecode.util.InputStreamUtil;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ClassLoader {

    public ClassFile readClassFile(String path) throws IOException {
        ClassFile classFile = new ClassFile();
        DataInputStream stream = InputStreamUtil.getDataInputStream(path);

        readMagic(classFile, stream);
        readMinorVersion(classFile, stream);
        readMajorVersion(classFile, stream);
        readConstantPoolCount(classFile, stream);
        readConstantPool(classFile, stream);
        readAccessFlags(classFile, stream);
        readThisClass(classFile, stream);
        readSuperClass(classFile, stream);
        readInterfacesCount(classFile, stream);
        readInterfaces(classFile, stream);
        readFieldCount(classFile, stream);
        readFields(classFile, stream);
        readMethodsCount(classFile, stream);
        readMethods(classFile, stream);
        readAttributesCount(classFile, stream);
        readAttributes(classFile, stream);

        return classFile;
    }

    private void readAttributes(ClassFile classFile, DataInputStream stream) {
        Attributes attributes = new Attributes(classFile.attributesCount);
        classFile.setAttributes(attributes);
        for (int i = 0; i < classFile.attributesCount; i++) {

        }
    }

    private void readAttributesCount(ClassFile classFile, DataInputStream stream) throws IOException {
        int attributesCount = stream.readUnsignedShort();
        classFile.setAttributesCount(attributesCount);
    }

    private void readMethods(ClassFile classFile, DataInputStream stream) throws IOException {
        Methods methods = new Methods();
        classFile.setMethods(methods);
        for (int i = 0; i < classFile.methodsCount; i++) {
            int accessFlags = stream.readUnsignedShort();
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();

            MethodInfo methodInfo = new MethodInfo();
            methodInfo.accessFlags = accessFlags;
            methodInfo.accessFlagEnum = MethodAccessFlagEnum.enumMap.get(accessFlags);

            methodInfo.nameIndex = nameIndex;
            methodInfo.nameConstantUtf8Info = (ConstantUtf8Info) classFile.constantPool.getConstantInfo(nameIndex);

            methodInfo.descriptorIndex = descriptorIndex;
            methodInfo.descriptorConstantUtf8Info = (ConstantUtf8Info) classFile.constantPool.getConstantInfo(descriptorIndex);

            methodInfo.attributesCount = attributesCount;
            methodInfo.attributes = new ArrayList<>(attributesCount);

            methods.addMethodInfo(methodInfo);

            for (int m = 0; m < attributesCount; m++) {
                int attributeNameIndex = stream.readUnsignedShort();
                String attributeName = classFile.constantPool.getConstantUtf8InfoStr(attributeNameIndex);
                int attributeLength = stream.readInt();

                switch (attributeName) {
//  --------------------------------------------------------------------------------------------------------------------
                    case AttributeConstants.SourceFile:
                        int sourceFileIndex = stream.readUnsignedShort();
                        SourceFileAttribute sourceFileAttribute = new SourceFileAttribute(attributeNameIndex, attributeLength);
                        sourceFileAttribute.sourcefileIndex = sourceFileIndex;
                        sourceFileAttribute.sourcefileConstantUtf8Info = (ConstantUtf8Info) classFile.constantPool.getConstantInfo(sourceFileIndex);
                        methodInfo.addAttribute(sourceFileAttribute);
                        break;
//  --------------------------------------------------------------------------------------------------------------------
//                    case AttributeConstants.Code:
//                        int maxStack = stream.readUnsignedShort();
//                        int maxLocals = stream.readUnsignedShort();
//                        int codeLength = stream.readInt();
//
//                        CodeAttribute codeAttribute = new CodeAttribute(attributeNameIndex, attributeLength);
//                        codeAttribute.maxStack = maxStack;
//                        codeAttribute.maxLocals = maxLocals;
//                        codeAttribute.codeLength = codeLength;
//
//                        byte[] byteCode = Utils.readNBytes(is, codeLength);
//
//                        Instruction[] instructions = readByteCode(byteCode, constantPool);
//
//                        int exceptionTableLength = is.readUnsignedShort();
//                        Exception[] exceptions = new Exception[exceptionTableLength];
//                        for (int i1 = 0; i1 < exceptionTableLength; i1++) {
//                            int etsp = is.readUnsignedShort();
//                            int etep = is.readUnsignedShort();
//                            int ethp = is.readUnsignedShort();
//                            int ctIdx = is.readUnsignedShort();
//
//                            // null => catch any exception
//                            String etClassname = null;
//                            if (ctIdx != 0) {
//                                etClassname = Utils.getClassName(constantPool, ctIdx);
//                            }
//
//                            Exception exception = new Exception(etsp, etep, ethp, etClassname);
//                            exceptions[i1] = exception;
//                        }
//                        ExceptionTable exceptionTable = new ExceptionTable(exceptions);
//                        int codeAttributeCount = is.readUnsignedShort();
//                        Attributes codeAttributes = readAttributes(is, codeAttributeCount, constantPool);
//
//                        attribute = new Code(maxStack, maxLocals, instructions, exceptionTable, codeAttributes);
//                        break;
//                    case AttributeConstants.LineNumberTable:
//                        int length = is.readUnsignedShort();
//                        LineNumberTable.Line[] lines = new LineNumberTable.Line[length];
//                        for (int i1 = 0; i1 < length; i1++) {
//                            lines[i1] = new LineNumberTable.Line(is.readUnsignedShort(), is.readUnsignedShort());
//                        }
//                        attribute = new LineNumberTable(lines);
//                        break;
//                    case AttributeConstants.BootstrapMethods:
//                        int bsmLen = is.readUnsignedShort();
//                        BootstrapMethods.BootstrapMethod[] bootstrapMethods = new BootstrapMethods.BootstrapMethod[bsmLen];
//                        for (int i1 = 0; i1 < bsmLen; i1++) {
//                            int bsmr = is.readUnsignedShort();
//                            int nbma = is.readUnsignedShort();
//                            Integer[] args = new Integer[nbma];
//                            for (int i2 = 0; i2 < nbma; i2++) {
//                                args[i2] = is.readUnsignedShort();
//                            }
//
//                            bootstrapMethods[i1] = new BootstrapMethods.BootstrapMethod(bsmr, args);
//                        }
//
//                        attribute = new BootstrapMethods(bootstrapMethods);
//                        break;
                    default:
                        byte[] bytes = new byte[attributeLength];
                        for (int l = 0; l < attributeLength; l++) {
                            bytes[l] = stream.readByte();
                        }
                        break;
                }
            }
        }
    }

    private void readMethodsCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 methodsCount
        int methodsCount = stream.readUnsignedShort();
        classFile.setMethodsCount(methodsCount);
    }

    private void readFields(ClassFile classFile, DataInputStream stream) throws IOException {
        Fields fields = new Fields(classFile.fieldCount);
        classFile.setFields(fields);

        for (int i = 0; i < classFile.fieldCount; i++) {
            int accessFlags = stream.readUnsignedShort();
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();

            if (attributesCount != 0) {
                throw new Error("Field Attribute Count != 0"); // TODO 暂时不实现解析字段属性
            }
            Attributes attributes = new Attributes(0);

            FieldInfo fieldInfo = new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, attributes);
            fields.addFieldInfo(fieldInfo);
        }
    }

    private void readFieldCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 fieldCount
        int fieldCount = stream.readUnsignedShort();
        classFile.setFieldCount(fieldCount);
    }

    private void readInterfaces(ClassFile classFile, DataInputStream stream) throws IOException {
        // interfaces
        Interfaces interfaces = new Interfaces(classFile.interfacesCount);
        classFile.setInterfaces(interfaces);

        for (int i = 0; i < classFile.interfacesCount; i++) {
            int constantPoolIndex = stream.readUnsignedShort();
            Interface anInterface = new Interface(constantPoolIndex);
            interfaces.addInterface(anInterface);
        }
    }

    private void readInterfacesCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 superClass
        int interfacesCount = stream.readUnsignedShort();
        classFile.setInterfacesCount(interfacesCount);
    }

    private void readSuperClass(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 superClass
        int superClass = stream.readUnsignedShort();
        classFile.setSuperClass(superClass);
    }

    private void readThisClass(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 thisClass
        int thisClass = stream.readUnsignedShort();
        classFile.setThisClass(thisClass);
    }

    private void readAccessFlags(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 accessFlags
        int accessFlags = stream.readUnsignedShort();
        classFile.setAccessFlags(accessFlags);
    }

    private void readConstantPool(ClassFile classFile, DataInputStream stream) throws IOException {
        // read constantPool
        ConstantPool constantPool = new ConstantPool(classFile.constantPoolCount - 1);
        classFile.setConstantPool(constantPool);

        for (int i = 1; i < classFile.constantPoolCount; i++) {
            int tag = stream.readUnsignedByte();
            switch (tag) {
                case ConstantInfoConstants.CONSTANT_Utf8 -> readConstantUtf8Info(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Integer -> readConstantIntegerInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Float -> readConstantFloatInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Long -> readConstantLongInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Double -> readConstantDoubleInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Class -> readConstantClassInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_String -> readConstantStringInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Fieldref -> readConstantFieldrefInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_Methodref -> readConstantMethodrefInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_InterfaceMethodref ->
                        readConstantInterfaceMethodRefInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_NameAndType -> readConstantNameAndTypeInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_MethodHandle -> readConstantMethodHandleInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_MethodType -> readConstantMethodtypeInfo(stream, constantPool);
                case ConstantInfoConstants.CONSTANT_InvokeDynamic ->
                        readConstantInvokeDynamicInfo(stream, constantPool);
                default -> throw new UnsupportedOperationException("Unsupported Constant Tag " + tag);
            }
        }

        for (ConstantInfo constantInfo : constantPool.constantInfoMap.values()) {
            if (constantInfo instanceof ConstantClassInfo constantClassInfo) {
                ConstantUtf8Info nameConstantUtf8Info = (ConstantUtf8Info) constantPool.constantInfoMap.get(constantClassInfo.nameIndex);
                constantClassInfo.setNameConstantUtf8Info(nameConstantUtf8Info);
            }
            if (constantInfo instanceof ConstantStringInfo constantStringInfo) {
                ConstantUtf8Info nameConstantUtf8Info = (ConstantUtf8Info) constantPool.constantInfoMap.get(constantStringInfo.stringIndex);
                constantStringInfo.setStringConstantUtf8Info(nameConstantUtf8Info);
            }
            if (constantInfo instanceof ConstantNameAndTypeInfo constantNameAndTypeInfo) {
                ConstantUtf8Info nameConstantUtf8Info = (ConstantUtf8Info) constantPool.constantInfoMap.get(constantNameAndTypeInfo.nameIndex);
                constantNameAndTypeInfo.setNameConstantUtf8Info(nameConstantUtf8Info);

                ConstantUtf8Info descriptionConstantUtf8Info = (ConstantUtf8Info) constantPool.constantInfoMap.get(constantNameAndTypeInfo.descriptionIndex);
                constantNameAndTypeInfo.setDescriptionConstantUtf8Info(descriptionConstantUtf8Info);
            }
            if (constantInfo instanceof ConstantFieldrefInfo constantFieldrefInfo) {
                ConstantClassInfo constantClassInfo = (ConstantClassInfo) constantPool.constantInfoMap.get(constantFieldrefInfo.classIndex);
                constantFieldrefInfo.setConstantClassInfo(constantClassInfo);

                ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) constantPool.constantInfoMap.get(constantFieldrefInfo.nameAndTypeIndex);
                constantFieldrefInfo.setConstantNameAndTypeInfo(constantNameAndTypeInfo);
            }
            if (constantInfo instanceof ConstantMethodrefInfo constantMethodrefInfo) {
                ConstantClassInfo constantClassInfo = (ConstantClassInfo) constantPool.constantInfoMap.get(constantMethodrefInfo.classIndex);
                constantMethodrefInfo.setConstantClassInfo(constantClassInfo);

                ConstantNameAndTypeInfo constantNameAndTypeInfo = (ConstantNameAndTypeInfo) constantPool.constantInfoMap.get(constantMethodrefInfo.nameAndTypeIndex);
                constantMethodrefInfo.setConstantNameAndTypeInfo(constantNameAndTypeInfo);
            }
            if (constantInfo instanceof ConstantMethodtypeInfo constantMethodtypeInfo) {
                ConstantUtf8Info descriptorConstantUtf8Info = (ConstantUtf8Info) constantPool.constantInfoMap.get(constantMethodtypeInfo.descriptorIndex);
                constantMethodtypeInfo.setDescriptorConstantUtf8Info(descriptorConstantUtf8Info);
            }
        }
    }

    private void readConstantPoolCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 constant_pool_count
        int constantPoolCount = stream.readUnsignedShort();
        classFile.setConstantPoolCount(constantPoolCount);
    }

    private void readMajorVersion(ClassFile classFile, DataInputStream stream) throws IOException {
        //u2 majorVersion
        int majorVersion = stream.readUnsignedShort();
        classFile.setMajorVersion(majorVersion);
    }

    private void readMinorVersion(ClassFile classFile, DataInputStream stream) throws IOException {
        //u2 minorVersion
        int minorVersion = stream.readUnsignedShort();
        classFile.setMinorVersion(minorVersion);
    }

    private void readMagic(ClassFile classFile, DataInputStream stream) throws IOException {
        //u4 magic
        int magic = stream.readInt(); // -889275714
        classFile.setMagic(magic);
    }

    private void readConstantInvokeDynamicInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInvokeDynamicInfo constantInvokeDynamicInfo = new ConstantInvokeDynamicInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantInvokeDynamicInfo);
    }

    private void readConstantMethodtypeInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantMethodtypeInfo constantMethodtypeInfo = new ConstantMethodtypeInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodtypeInfo);
    }

    private void readConstantMethodHandleInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantMethodHandleInfo constantMethodHandleInfo = new ConstantMethodHandleInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodHandleInfo);
    }

    private void readConstantNameAndTypeInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantNameAndTypeInfo constantNameAndTypeInfo = new ConstantNameAndTypeInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantNameAndTypeInfo);
    }

    private void readConstantInterfaceMethodRefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInterfaceMethodRefInfo constantInterfaceMethodRefInfo = new ConstantInterfaceMethodRefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantInterfaceMethodRefInfo);
    }

    private void readConstantMethodrefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantMethodrefInfo constantMethodrefInfo = new ConstantMethodrefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodrefInfo);
    }

    private void readConstantFieldrefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantFieldrefInfo constantFieldrefInfo = new ConstantFieldrefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantFieldrefInfo);
    }

    private void readConstantStringInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantStringInfo constantStringInfo = new ConstantStringInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantStringInfo);
    }

    private void readConstantClassInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantClassInfo constantClassInfo = new ConstantClassInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantClassInfo);
    }

    private void readConstantDoubleInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantDoubleInfo = new ConstantDoubleInfo(stream.readDouble());
        constantPool.addConstantInfo(constantDoubleInfo);
    }

    private void readConstantLongInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantLongInfo constantLongInfo = new ConstantLongInfo(stream.readLong());
        constantPool.addConstantInfo(constantLongInfo);
    }

    private void readConstantFloatInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantFloatInfo constantFloatInfo = new ConstantFloatInfo(stream.readFloat());
        constantPool.addConstantInfo(constantFloatInfo);
    }

    private void readConstantIntegerInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantIntegerInfo constantIntegerInfo = new ConstantIntegerInfo(stream.readInt());
        constantPool.addConstantInfo(constantIntegerInfo);
    }

    private void readConstantUtf8Info(DataInputStream stream, ConstantPool constantPool) throws IOException {
        int length = stream.readUnsignedShort();
        byte[] bytes = new byte[length];
        stream.read(bytes);
        ConstantUtf8Info constantUtf8Info = new ConstantUtf8Info(length, bytes);
        constantPool.addConstantInfo(constantUtf8Info);
    }

}