package haindor.vm.bytecode;

import java.util.ArrayList;
import java.util.List;

public class Fields {

    public final List<FieldInfo> fieldInfos;

    public Fields(int fieldInfoCount) {
        this.fieldInfos = new ArrayList<>();
    }

    public void addFieldInfo(FieldInfo fieldInfo) {
        this.fieldInfos.add(fieldInfo);
    }
}
