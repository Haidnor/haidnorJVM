package haidnor.vm.bytecode;

import haidnor.vm.bytecode.method.MethodInfo;

import java.util.ArrayList;
import java.util.List;

public class Methods {

    public final List<MethodInfo> methods;

    public Methods() {
        this.methods = new ArrayList<>();
    }

    public void addMethodInfo(MethodInfo methodInfo) {
        methods.add(methodInfo);
    }

}
