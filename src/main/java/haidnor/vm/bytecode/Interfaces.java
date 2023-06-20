package haidnor.vm.bytecode;

import java.util.ArrayList;
import java.util.List;

public class Interfaces {

    /**
     * interfaces 数组中的每个值都必须是 constant_pool 表中的有效索引。 interfaces[i] 的每个值处的 constant_pool 条目，其中 0 ≤ < interfaces_count ，必须是一个 CONSTANT_Class_info 结构，表示一个接口，该接口是此类或接口类型的直接超接口，从左到右该类型的来源中给出的顺序。
     */
    public final List<Interface> interfaces;

    public Interfaces(int interfacesCount) {
        this.interfaces = new ArrayList<>(interfacesCount);
    }

    public void addInterface(Interface anInterface) {
        this.interfaces.add(anInterface);
    }

}
