package haidnor.vm.instruction.references;

import haidnor.vm.instruction.AbstractInstruction;
import haidnor.vm.runtime.Frame;
import haidnor.vm.util.CodeStream;
import haidnor.vm.util.ConstantPoolUtil;
import haidnor.vm.util.SignatureUtil;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.ConstantMethodref;
import org.apache.bcel.classfile.ConstantPool;
import org.apache.bcel.classfile.Utility;

import java.util.Objects;

@Slf4j
public class InvokeVirtualInst extends AbstractInstruction {

    private final int operand;

    public InvokeVirtualInst(CodeStream codeStream) {
        super(codeStream);
        this.operand = codeStream.readU2();
    }

    @Override
    @SneakyThrows
    public void execute(Frame frame) {
        log.info("execute: INVOKEVIRTUAL"); // 调用所有虚方法
        ConstantPool constantPool = frame.getConstantPool();
        ConstantPoolUtil constantPoolUtil = frame.getConstantPoolUtil();

        ConstantMethodref methodref = constantPool.getConstant(operand);

        String className = constantPoolUtil.getBelongClassName(methodref);
        String methodName = constantPoolUtil.getMethodName(methodref);
        String methodSignature = constantPoolUtil.getMethodSignature(methodref);

        // 解析方法签名得到方法的返回类型
        String returnType = Utility.methodSignatureReturnType(methodSignature, false);

        //  系统类反射 自定义类另外处理
        if (className.startsWith("java")) {
            // 执行方法的参数列表
            Class<?>[] parameterTypeArr = SignatureUtil.getParameterTypes(methodSignature);
            // 执行方法的参数值
            Object[] stacksValueArr = frame.popStacksValue(parameterTypeArr.length);
            // 执行方法的示例对象
            Object object = frame.pop().getValue();

            // 如果 jvm 要执行一个方法, 1.把这个方法对象压入操作数栈 2.把这个方法的参数值压入操作出栈(一个参数占用一个操作数栈帧) 3.执行 INVOKEVIRTUAL
            // 执行 INVOKEVIRTUAL 的流程
            // 1.从操作栈中取出全部参数
            // 2.从操作栈中取出执行方法的对象
            // 3.使用对象执行方法

            java.lang.reflect.Method javaMethod = object.getClass().getMethod(methodName, parameterTypeArr);

            if (Objects.equals(Const.getTypeName(Const.T_VOID), returnType)) {     // void 调用的方法无返回值
                javaMethod.invoke(object, stacksValueArr);
            } else {
                // TODO
                // descriptorStream.pushField(javaMethod.invoke(obj, params), frame);
            }
        }
    }

    @Override
    public int nextOffSet() {
        return super.nextOffSet() + 2;
    }
}
