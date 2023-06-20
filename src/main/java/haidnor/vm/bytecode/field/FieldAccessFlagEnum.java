package haidnor.vm.bytecode.field;

/*
 https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.5
*/

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JVM 字段访问权限标识常量
 *
 * @author wang xiang
 */
public enum FieldAccessFlagEnum {
    /**
     * Declared public, may be accessed from outside its package.
     */
    ACC_PUBLIC(0x0001),
    /**
     * Declared private, usable only within the defining class.
     */
    ACC_PRIVATE(0x0002),
    /**
     * Declared protected, may be accessed within subclasses.
     */
    ACC_PROTECTED(0x0004),
    /**
     * Declared static.
     */
    ACC_STATIC(0x0008),
    /**
     * Declared final, never directly assigned to after object construction (JLS §17.5).
     */
    ACC_FINAL(0x0010),
    /**
     * Declared volatile, cannot be cached.
     */
    ACC_VOLATILE(0x0040),
    /**
     * Declared transient, not written or read by a persistent object manager.
     */
    ACC_TRANSIENT(0x0080),
    /**
     * Declared synthetic, not present in the source code.
     */
    ACC_SYNTHETIC(0x1000),
    /**
     * Declared as an element of an enum.
     */
    ACC_ENUM(0x4000);

    public static final Map<Integer, FieldAccessFlagEnum> enumMap;

    static {
        enumMap = Arrays.stream(FieldAccessFlagEnum.values())
                .collect(Collectors.toMap(FieldAccessFlagEnum -> FieldAccessFlagEnum.value, Function.identity()));
    }

    public final int value;

    FieldAccessFlagEnum(int value) {
        this.value = value;
    }

}
