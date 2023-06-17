package haindor.vm.bytecode;

/*
   https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1

   A class file consists of a single ClassFile structure:
   ClassFile {
        u4             magic;
        u2             minor_version;
        u2             major_version;
        u2             constant_pool_count;
        cp_info        constant_pool[constant_pool_count-1];
        u2             access_flags;
        u2             this_class;
        u2             super_class;
        u2             interfaces_count;
        u2             interfaces[interfaces_count];
        u2             fields_count;
        field_info     fields[fields_count];
        u2             methods_count;
        method_info    methods[methods_count];
        u2             attributes_count;
        attribute_info attributes[attributes_count];
    }
 */
public class ClassFile {

    /**
     * magic 项提供标识 class 文件格式的幻数；它的值为 0xCAFEBABE 。
     */
    public int magic;

    /**
     * 次版本号
     * <p>
     * minor_version 和 major_version 项的值是此 class 文件的次版本号和主要版本号。主要版本号和次要版本号共同决定了 class 文件格式的版本。
     * 如果 class 文件有主版本号M和次版本号m，我们将其 class 文件格式的版本表示为M.m。
     * 因此， class 文件格式版本可以按字典顺序排序，例如，1.5 < 2.0 < 2.1。
     * <p>
     * 当且仅当 v 位于某个连续范围 Mi.0 ≤ v ≤ Mj.m 时，Java 虚拟机实现才能支持版本 v 的 class 文件格式。
     * Java 虚拟机实现所遵循的 Java SE 平台的版本级别负责确定该范围。
     * <p>
     * JDK 1.0.2 版中的 Oracle Java 虚拟机实现支持 class 文件格式版本 45.0 到 45.3（含）。
     * JDK 版本 1.1.* 支持 45.0 到 45.65535（含）范围内的 class 文件格式版本。
     * 对于 k ≥ 2，JDK 版本 1.k 支持 45.0 到 44+k.0（含）范围内的 class 文件格式版本。
     */
    public int minorVersion;

    /**
     * 主版本号
     */
    public int majorVersion;

    /**
     * 常量池计数器
     * <p>
     * constant_pool_count 项的值等于 constant_pool 表中的条目数加一。
     * 如果 constant_pool 索引大于零且小于 constant_pool_count ，则它被认为是有效的，但 §4.4.5 中提到的 long 和 double 类型的常量除外。
     * <p>
     * 长度 U2，从 1 开始，表示常量池中有多少项常量。即 constant_pool_count=1 表示常量池中有 0 个常量项。
     * 通常写代码时都是从0开始的，但是这里的常量池却是从1开始，因为它把第0项常量空出来了。这是为了满足后面某些指向常量池的索引值的数据在特定情况下需要表达
     * “不需要引用任何一个常量池项”的含义，这种情况可以用索引值0表示
     */
    public int constantPoolCount;

    /**
     * 常量池表
     * <p>
     * constant_pool 是一个结构表（第 4.4 节），表示各种字符串常量、类和接口名称、字段名称以及在 ClassFile 结构及其子结构中引用的其他常量。每个 constant_pool 表条目的格式由其第一个“标记”字节指示。
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.4
     * <p>
     * constant_pool 表的索引从 1 到 constant_pool_count - 1。
     */
    public ConstantPool constantPool;

    /**
     * access_flags 项的值是标志的掩码，用于表示对此类或接口的访问权限和属性。表 4.1-A 中指定了设置时每个标志的解释。
     * <p>
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1-200-E.1
     * <p>
     * Flag Name	        Value	        Interpretation
     * ACC_PUBLIC	        0x0001	        Declared public; may be accessed from outside its package.
     * ACC_FINAL	        0x0010	        Declared final; no subclasses allowed.
     * ACC_SUPER	        0x0020	        Treat superclass methods specially when invoked by the invokespecial instruction.
     * ACC_INTERFACE	    0x0200	        Is an interface, not a class.
     * ACC_ABSTRACT	        0x0400	        Declared abstract; must not be instantiated.
     * ACC_SYNTHETIC	    0x1000	        Declared synthetic; not present in the source code.
     * ACC_ANNOTATION	    0x2000	        Declared as an annotation type.
     * ACC_ENUM	            0x4000	        Declared as an enum type.
     */
    public int accessFlags;

    /**
     * 类索引 (class文件存储的类名类似完全限定名)
     * <p>
     * this_class 项的值必须是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是一个 CONSTANT_Class_info 结构（§4.4.1），表示此 class 文件定义的类或接口。
     */
    public int thisClass;

    /**
     * 超类索引
     * <p>
     * 对于类， super_class 项的值必须为零或必须是 constant_pool 表中的有效索引。
     * 如果 super_class 项的值不为零，则该索引处的 constant_pool 条目必须是 CONSTANT_Class_info 结构，表示此 class 文件定义的类的直接超类。
     * 直接超类及其任何超类都不能在其 ClassFile 结构的 access_flags 项中设置 ACC_FINAL 标志。
     * <p>
     * 如果 super_class 项的值为零，则此 class 文件必须表示类 Object ，这是唯一没有直接超类的类或接口。
     * <p>
     * 对于接口， super_class 项的值必须始终是 constant_pool 表中的有效索引。
     * 该索引处的 constant_pool 条目必须是表示类 Object 的 CONSTANT_Class_info 结构。
     */
    public int superClass;

    /**
     * 接口数
     * <p>
     * interfaces_count 项的值给出了此类或接口类型的直接超接口的数量。
     */
    public int interfacesCount;

    /**
     * 接口索引表，给出该类实现的所有接口的名字
     * <p>
     * interfaces 数组中的每个值都必须是 constant_pool 表中的有效索引。
     * interfaces[i] 的每个值处的 constant_pool 条目，其中 0 ≤ < interfaces_count ，必须是一个 CONSTANT_Class_info 结构，
     * 表示一个接口，该接口是此类或接口类型的直接超接口，从左到右该类型的来源中给出的顺序。
     */
    public Interfaces interfaces;

    /**
     * 字段数
     * <p>
     * fields_count 项的值给出了 fields 表中 field_info 结构的数量。 field_info 结构表示由此类或接口类型声明的所有字段，包括类变量和实例变量。
     */
    public int fieldCount;

    /**
     * 字段结构，存储字段信息
     * <p>
     * fields 表中的每个值都必须是一个 field_info 结构（§4.5），给出此类或接口中字段的完整描述。
     * fields 表仅包含此类或接口声明的那些字段。
     * 它不包括表示从超类或超接口继承的字段的项目。
     */
    public Fields fields;

    /**
     * 方法数
     * <p>
     * methods_count 项的值给出了 methods 表中 method_info 结构的数量。
     */
    public int methodsCount;

    /**
     * 方法结构，存储方法信息
     * <p>
     * methods 表中的每个值都必须是一个 method_info 结构（§4.6），给出此类或接口中方法的完整描述。
     * 如果 method_info 结构的 access_flags 项中没有设置 ACC_NATIVE 和 ACC_ABSTRACT 标志，则还提供实现该方法的Java 虚拟机指令。
     */
//    public Methods methods;

    /**
     * 属性数
     * <p>
     * attributes_count 项的值给出了该类的 attributes 表中的属性数。
     */
    public int attributesCount;

    /**
     * 属性结构，存储属性信息
     * attributes 表的每个值都必须是一个 attribute_info 结构（§4.7）。
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7
     * <p>
     * 本规范定义的出现在 ClassFile 结构的 attributes 表中的属性在表 4.7-C 中列出。
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7-320
     * <p>
     * 有关定义为出现在 ClassFile 结构的 attributes 表中的属性的规则在§4.7 中给出。
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7
     * <p>
     * 有关 ClassFile 结构的 attributes 表中非预定义属性的规则在§4.7.1 中给出。
     * https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.7.1
     */
    public Attributes attributes;

}
