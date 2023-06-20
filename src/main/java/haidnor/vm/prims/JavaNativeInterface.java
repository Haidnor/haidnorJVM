package haidnor.vm.prims;


import haidnor.vm.bcel.classfile.Code;
import haidnor.vm.bcel.classfile.MethodInfo;
import haidnor.vm.intepreter.BytecodeInterpreter;
import haidnor.vm.runtime.JavaThread;
import haidnor.vm.runtime.JavaVFrame;
import haidnor.vm.runtime.Threads;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JavaNativeInterface {

    public static void callStaticMethod(MethodInfo method) {
        JavaThread thread = Threads.currentThread();

        if (!method.isStatic()) {
            throw new Error("只能调用静态方法");
        }
        Code attribute = (Code) method.getAttributes()[0];

        // 创建栈帧
        JavaVFrame frame = new JavaVFrame(attribute.getMaxLocals(), method);
        thread.getStack().push(frame);

        log.info("stack frame index:" + thread.getStack().size());

        // 执行任务交给字节码解释器
        BytecodeInterpreter.run(thread, method);
    }

}
