package haidnor.vm.bytecode.constant;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum ConstantInfoEnum {

    CONSTANT_Utf8(1),
    CONSTANT_Integer(3),
    CONSTANT_Float(4),
    CONSTANT_Long(5),
    CONSTANT_Double(6),
    CONSTANT_Class(7),
    CONSTANT_String(8),
    CONSTANT_Fieldref(9),
    CONSTANT_Methodref(10),
    CONSTANT_InterfaceMethodref(11),
    CONSTANT_NameAndType(12),
    CONSTANT_MethodHandle(15),
    CONSTANT_MethodType(16),
    CONSTANT_InvokeDynamic(18);

    public static final Map<Integer, ConstantInfoEnum> enumMap;

    static {
        enumMap = Arrays.stream(ConstantInfoEnum.values())
                .collect(Collectors.toMap(constantInfoEnum -> constantInfoEnum.tag, Function.identity()));
    }

    public final int tag;

}
