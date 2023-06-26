package haidnor.vm.core;


import haidnor.vm.runtime.Frame;
import haidnor.vm.runtime.JvmThread;
import haidnor.vm.util.ThreadHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.bcel.classfile.Method;

@Slf4j
public class JavaNativeInterface {

    public static void callStaticMethod(Method method) {
        JvmThread jThread = ThreadHolder.get();

        // 创建栈帧
        Frame frame = new Frame();
        jThread.push(frame);

        // 执行任务交给字节码解释器
        Interpreter.run(method);
    }

}
