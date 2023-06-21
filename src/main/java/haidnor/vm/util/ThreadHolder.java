package haidnor.vm.util;

import haidnor.vm.runtime.JvmThread;

public abstract class ThreadHolder {

    private static final ThreadLocal<JvmThread> holder = new ThreadLocal<>();

    public static void set(JvmThread thread) {
        holder.set(thread);
    }

    public static JvmThread get() {
        return holder.get();
    }

}
