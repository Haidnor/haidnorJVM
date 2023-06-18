package haidnor.vm;

import haindor.vm.bytecode.*;
import haindor.vm.bytecode.constant.*;
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

        FileInputStream fileInputStream = new FileInputStream(path);
        DataInputStream stream = new DataInputStream(fileInputStream);

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


//        // u2 methodsCount
//        int methodsCount = stream.readUnsignedShort();
//        classFile.setMethodsCount(methodsCount);
//
//        for (int m = 0; m < methodsCount; m++) {
//
//        }

    }

    private static void readFields(ClassFile classFile, DataInputStream stream) throws IOException {
        for (int f = 0; f < classFile.fieldCount; f++) {
            int accessFlags = stream.readUnsignedShort();
            int nameIndex = stream.readUnsignedShort();
            int descriptorIndex = stream.readUnsignedShort();
            int attributesCount = stream.readUnsignedShort();

            FieldInfo fieldInfo = new FieldInfo(accessFlags, nameIndex, descriptorIndex, attributesCount, null);
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

        for (int y = 0; y < classFile.interfacesCount; y++) {
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
                default -> throw new UnsupportedOperationException("un parse cp " + tag);
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
