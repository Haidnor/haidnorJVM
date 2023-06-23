package haidnor.vm.instruction.references;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.StackValue;
import haidnor.vm.util.CodeStream;
import haidnor.vm.util.ConstantPoolUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.ConstantFieldref;
import org.apache.bcel.classfile.ConstantPool;

import java.lang.reflect.Field;

@Slf4j
public class GetStaticInst extends AbstractInstruction {

    private final int operand;

    public GetStaticInst(CodeStream codeStream) {
        super(codeStream);
        this.operand = codeStream.readU2();
    }
    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        log.info("execute: GETSTATIC");    // 获取字段符号引用指定的对象或者值(类的静态字段 static 修饰),并将其压入操作数栈
        ConstantPool constantPool = frame.getConstantPool();
        ConstantPoolUtil constantPoolUtil = frame.getConstantPoolUtil();

        ConstantFieldref constFieldref = constantPool.getConstant(operand);

        // 字段所属的 Java 类
        String className = constantPoolUtil.getBelongClassName(constFieldref);
        String fieldName = constantPoolUtil.getFieldName(constFieldref);

        Class<?> clazz = Class.forName(className.replace('/', '.'));
        Field field = clazz.getField(fieldName);
        Object staticFiledValue = field.get(null);       // 获取静态字段上的值

        frame.push(new StackValue(Const.T_OBJECT, staticFiledValue));
    }

    @Override
    public int nextOffSet() {
        return super.nextOffSet() + 2;
    }

}
