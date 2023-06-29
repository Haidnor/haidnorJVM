package haidnor.jvm.instruction.references;

import haidnor.jvm.instruction.Instruction;
import haidnor.jvm.rtda.heap.Instance;
import haidnor.jvm.rtda.heap.Klass;
import haidnor.jvm.rtda.metaspace.Metaspace;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.CodeStream;
import haidnor.jvm.util.ConstantPoolUtil;
import lombok.SneakyThrows;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.ConstantClass;
import org.apache.bcel.classfile.ConstantPool;

public class NEW extends Instruction {

    private final int constantClassIndex;

    public NEW(CodeStream codeStream) {
        super(codeStream);
        this.constantClassIndex = codeStream.readUnsignedShort(this);
    }

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        ConstantPool constantPool = frame.getConstantPool();
        ConstantPoolUtil constantPoolUtil = frame.getConstantPoolUtil();
        ConstantClass constantClass = constantPool.getConstant(constantClassIndex);
        String className = constantPoolUtil.getClassName(constantClass);

        Klass aKlass = Metaspace.getJavaClass(className);
        Instance instance;
        if (aKlass == null) {
            // 如果在元空间中找不到已加载的类,则开始进行类加载流程
            Klass newKlass = frame.getMetaClass().getClassLoader().loadClass(className);
            instance = newKlass.newInstance();
        } else {
            instance = aKlass.newInstance();
        }
        frame.push(new StackValue(Const.T_REFERENCE, instance));
    }

}
