package haindor.vm.bytecode;

import haindor.vm.bytecode.constant.ConstantInfo;
import haindor.vm.bytecode.constant.ConstantUtf8Info;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
常量池表

https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4

Java 虚拟机指令不依赖于类、接口、类实例或数组的运行时布局。相反，指令引用 constant_pool 表中的符号信息。

所有 constant_pool 表条目都具有以下通用格式：
    cp_info {
        u1 tag;
        u1 info[];
    }

constant_pool 表中的每一项都必须以一个 1 字节的标记开头，指示 cp_info 条目的类型。
info 数组的内容随着 tag 的值而变化。下表中列出了有效标签及其值。
每个标记字节后面必须跟两个或更多字节，提供有关特定常量的信息。附加信息的格式随标签值的不同而不同。

    Constant Type	               Value               Description
    ----------------------------------------------------------------------
    CONSTANT_Utf8	                1                   UTF-8 编码的字符串
    CONSTANT_Integer	            3                   整形字面量
    CONSTANT_Float	                4                   浮点型字面量
    CONSTANT_Long	                5                   长整型字面量
    CONSTANT_Double	                6                   双精度浮点型字面量
    CONSTANT_Class	                7                   类或接口的符号引用
    CONSTANT_String	                8                   字符串类型字面量
    CONSTANT_Fieldref	            9                   字段的符号引用
    CONSTANT_Methodref	            10                  类中方法的符号引用
    CONSTANT_InterfaceMethodref	    11                  接口中方法的符号引用
    CONSTANT_NameAndType	        12                  字段或方法的符号引用
    CONSTANT_MethodHandle	        15                  表示方法句柄 (since JDK7)
    CONSTANT_MethodType	            16                  标志方法类型 (since JDK7)
    CONSTANT_InvokeDynamic	        18                  一个动态方法的调用点 (since JDK7)
*/
public class ConstantPool {

    public final List<ConstantInfo> infos;

    public final Map<Integer, ConstantInfo> constantInfoMap;

    public ConstantPool(int count) {
        this.infos = new ArrayList<>(count);
        this.constantInfoMap = new LinkedHashMap<>(count);
    }

    public void addConstantInfo(ConstantInfo constantInfo) {
        this.infos.add(constantInfo);
        this.constantInfoMap.put(infos.size(), constantInfo);
    }

    public String getConstantUtf8InfoStr(int constantPoolIndex) {
        ConstantInfo constantInfo = constantInfoMap.get(constantPoolIndex);
        if (constantInfo instanceof ConstantUtf8Info constantUtf8Info) {
            return constantUtf8Info.utf8Str;
        }
        throw new Error("getConstantUtf8InfoStr error");
    }

}
