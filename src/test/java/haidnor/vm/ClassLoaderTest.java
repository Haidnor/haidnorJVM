package haidnor.vm;

import haindor.vm.bytecode.*;
import haindor.vm.bytecode.constant.*;
import haindor.vm.bytecode.method.MethodInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Slf4j
public class ClassLoaderTest {

    @Test
    public void test_() throws Exception {
        String path = "D:\\java_project\\JavaClassTest\\out\\production\\JavaClassTest\\Main.class";

        ClassFile classFile = new ClassFile();
        DataInputStream stream = new DataInputStream(new FileInputStream(path));

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
//        readMethods(classFile, stream);
//        readAttributesCount(classFile, stream);
//        readAttributes(classFile, stream);
    }

    private static void readAttributes(ClassFile classFile, DataInputStream stream) {
        Attributes attributes = new Attributes(classFile.attributesCount);
        classFile.setAttributes(attributes);
        for (int i = 0; i < classFile.attributesCount; i++) {

        }
    }

    private static void readAttributesCount(ClassFile classFile, DataInputStream stream) throws IOException {
        int attributesCount = stream.readUnsignedShort();
        classFile.setAttributesCount(attributesCount);
    }

    private static void readMethods(ClassFile classFile, DataInputStream stream) throws IOException {
        Methods methods = new Methods();
        classFile.setMethods(methods);
        for (int i = 0; i < classFile.methodsCount; i++) {
            int accessFlags = stream.readUnsignedShort();
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();
            MethodInfo methodInfo = new MethodInfo(accessFlags, nameIndex, descriptorIndex, attributesCount);
            methods.addMethodInfo(methodInfo);

            for (int m = 0; m < attributesCount; m++) {
                int attributeNameIndex = stream.readUnsignedShort();
//                String attributeName = Utils.getString(constantPool, attributeNameIndex);
            }
        }
    }

    private static void readMethodsCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 methodsCount
        int methodsCount = stream.readUnsignedShort();
        classFile.setMethodsCount(methodsCount);
    }

    private static void readFields(ClassFile classFile, DataInputStream stream) throws IOException {
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

    private static void readFieldCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 fieldCount
        int fieldCount = stream.readUnsignedShort();
        classFile.setFieldCount(fieldCount);
    }

    private static void readInterfaces(ClassFile classFile, DataInputStream stream) throws IOException {
        // interfaces
        Interfaces interfaces = new Interfaces(classFile.interfacesCount);
        classFile.setInterfaces(interfaces);

        for (int i = 0; i < classFile.interfacesCount; i++) {
            int constantPoolIndex = stream.readUnsignedShort();
            Interface anInterface = new Interface(constantPoolIndex);
            interfaces.addInterface(anInterface);
        }
    }

    private static void readInterfacesCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 superClass
        int interfacesCount = stream.readUnsignedShort();
        classFile.setInterfacesCount(interfacesCount);
    }

    private static void readSuperClass(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 superClass
        int superClass = stream.readUnsignedShort();
        classFile.setSuperClass(superClass);
    }

    private static void readThisClass(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 thisClass
        int thisClass = stream.readUnsignedShort();
        classFile.setThisClass(thisClass);
    }

    private static void readAccessFlags(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 accessFlags
        int accessFlags = stream.readUnsignedShort();
        classFile.setAccessFlags(accessFlags);
    }

    private static void readConstantPool(ClassFile classFile, DataInputStream stream) throws IOException {
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

    private static void readConstantPoolCount(ClassFile classFile, DataInputStream stream) throws IOException {
        // u2 constant_pool_count
        int constantPoolCount = stream.readUnsignedShort();
        classFile.setConstantPoolCount(constantPoolCount);
    }

    private static void readMajorVersion(ClassFile classFile, DataInputStream stream) throws IOException {
        //u2 majorVersion
        int majorVersion = stream.readUnsignedShort();
        classFile.setMajorVersion(majorVersion);
    }

    private static void readMinorVersion(ClassFile classFile, DataInputStream stream) throws IOException {
        //u2 minorVersion
        int minorVersion = stream.readUnsignedShort();
        classFile.setMinorVersion(minorVersion);
    }

    private static void readMagic(ClassFile classFile, DataInputStream stream) throws IOException {
        //u4 magic
        int magic = stream.readInt(); // -889275714
        classFile.setMagic(magic);
    }

    private static void readConstantInvokeDynamicInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInvokeDynamicInfo constantInvokeDynamicInfo = new ConstantInvokeDynamicInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantInvokeDynamicInfo);
    }

    private static void readConstantMethodtypeInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantMethodtypeInfo = new ConstantMethodtypeInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodtypeInfo);
    }

    private static void readConstantMethodHandleInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantMethodHandleInfo = new ConstantMethodHandleInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodHandleInfo);
    }

    private static void readConstantNameAndTypeInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantNameAndTypeInfo = new ConstantNameAndTypeInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantNameAndTypeInfo);
    }

    private static void readConstantInterfaceMethodRefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantInterfaceMethodRefInfo = new ConstantInterfaceMethodRefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantInterfaceMethodRefInfo);
    }

    private static void readConstantMethodrefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantMethodrefInfo = new ConstantMethodrefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantMethodrefInfo);
    }

    private static void readConstantFieldrefInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantFieldrefInfo = new ConstantFieldrefInfo(stream.readUnsignedShort(), stream.readUnsignedShort());
        constantPool.addConstantInfo(constantFieldrefInfo);
    }

    private static void readConstantStringInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantStringInfo = new ConstantStringInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantStringInfo);
    }

    private static void readConstantClassInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantClassInfo = new ConstantClassInfo(stream.readUnsignedShort());
        constantPool.addConstantInfo(constantClassInfo);
    }

    private static void readConstantDoubleInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantDoubleInfo = new ConstantDoubleInfo(stream.readDouble());
        constantPool.addConstantInfo(constantDoubleInfo);
    }

    private static void readConstantLongInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantLongInfo = new ConstantLongInfo(stream.readLong());
        constantPool.addConstantInfo(constantLongInfo);
    }

    private static void readConstantFloatInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantFloatInfo = new ConstantFloatInfo(stream.readFloat());
        constantPool.addConstantInfo(constantFloatInfo);
    }

    private static void readConstantIntegerInfo(DataInputStream stream, ConstantPool constantPool) throws IOException {
        ConstantInfo constantIntegerInfo = new ConstantIntegerInfo(stream.readInt());
        constantPool.addConstantInfo(constantIntegerInfo);
    }

    private static void readConstantUtf8Info(DataInputStream stream, ConstantPool constantPool) throws IOException {
        int length = stream.readUnsignedShort();
        byte[] bytes = new byte[length];
        stream.read(bytes);
        ConstantInfo constantUtf8Info = new ConstantUtf8Info(length, bytes);
        constantPool.addConstantInfo(constantUtf8Info);
    }

}
