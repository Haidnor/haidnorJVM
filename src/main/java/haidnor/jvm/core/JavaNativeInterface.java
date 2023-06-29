package haidnor.jvm.core;


import haidnor.jvm.rtda.heap.KlassMethod;
import haidnor.jvm.runtime.Frame;
import haidnor.jvm.runtime.JvmThread;
import haidnor.jvm.runtime.StackValue;
import haidnor.jvm.util.JvmThreadHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.Const;
import org.apache.bcel.classfile.LocalVariable;
import org.apache.bcel.classfile.LocalVariableTable;
import org.apache.bcel.classfile.Utility;

@Slf4j
public class JavaNativeInterface {

    public static void callMainStaticMethod(KlassMethod klassMethod) {
        JvmThread jvmThread = JvmThreadHolder.get();
        Frame frame = new Frame(jvmThread, klassMethod);
        jvmThread.push(frame);
        Interpreter.executeFrame(frame);
    }

    public static void callStaticMethod(Frame lastFrame, KlassMethod klassMethod) {
        org.apache.bcel.classfile.Method method = klassMethod.javaMethod;

        JvmThread jvmThread = JvmThreadHolder.get();
        Frame newFrame = new Frame(jvmThread, klassMethod);

        String signature = method.getSignature();
        String[] argumentTypes = Utility.methodSignatureArgumentTypes(signature);

        int argumentSlotSize = argumentTypes.length;
        if (method.getAccessFlags() != Const.ACC_STATIC) {
            argumentSlotSize++;
        }

        // 静态方法调用传参
        // 将上一个栈帧操作数栈中数据弹出,存入下一个栈帧的局部变量表中
        for (int i = argumentSlotSize - 1; i >= 0; i--) {
            LocalVariableTable localVariableTable = method.getLocalVariableTable();
            if (localVariableTable == null) {
                break;
            }
            LocalVariable[] localVariableArr = localVariableTable.getLocalVariableTable();
            LocalVariable localVariable = localVariableArr[i];
            int slotIndex = localVariable.getIndex();
            StackValue stackValue = lastFrame.pop();
            newFrame.slotSet(slotIndex, stackValue);
        }
        jvmThread.push(newFrame);
        Interpreter.executeFrame(newFrame);
    }


}
