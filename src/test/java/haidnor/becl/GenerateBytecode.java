package haidnor.becl;


import haidnor.vm.bcel.Constants;
import haidnor.vm.bcel.classfile.JavaClass;
import haidnor.vm.bcel.generic.*;

public class GenerateBytecode {

    public static void main(String[] args) throws Exception {
        // 创建 ClassGen 对象
        ClassGen cg = new ClassGen("MyClass", "java.lang.Object", "<generated>", Constants.ACC_PUBLIC, null);

        // 创建 MethodGen 对象
        Type returnType = Type.VOID;
        Type[] argTypes = new Type[] { Type.STRING };
        String[] argNames = new String[] { "arg" };
        String methodName = "myMethod";
        MethodGen mg = new MethodGen(Constants.ACC_PUBLIC | Constants.ACC_STATIC, returnType, argTypes, argNames, methodName, "MyClass", new InstructionList(), cg.getConstantPool());

        // 向方法中添加指令
        InstructionList il = mg.getInstructionList();
        il.append(new GETSTATIC(cg.getConstantPool().addFieldref(System.class.getName(), "out", "Ljava/io/PrintStream;")));
        il.append(new LDC(cg.getConstantPool().addString("Hello world!")));
        il.append(new INVOKEVIRTUAL(cg.getConstantPool().addMethodref(java.io.PrintStream.class.getName(), "println", "(Ljava/lang/String;)V")));
        il.append(new RETURN());

        // 将 MethodGen 添加到 ClassGen 中
        cg.addMethod(mg.getMethod());

        // 生成 Java 类文件
        JavaClass jc = cg.getJavaClass();
        jc.dump("MyClass.class");
    }

}