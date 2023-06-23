package haidnor.vm.util;

import org.apache.bcel.classfile.Attribute;
import org.apache.bcel.classfile.Code;
import org.apache.bcel.classfile.LineNumber;
import org.apache.bcel.classfile.LineNumberTable;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class CodeUtil {

    public static LineNumberTable getLineNumberTable(Code code) {
        Attribute[] attributes = code.getAttributes();
        for (Attribute attribute : attributes) {
            if (attribute instanceof LineNumberTable) {
                return (LineNumberTable) attribute;
            }
        }
        throw new Error("can not find code LineNumberTable Attribute");
    }

    public static Set<Integer> getStartPcSet(Code code) {
        LineNumberTable lineNumberTable = getLineNumberTable(code);
        LineNumber[] lineNumberArr = lineNumberTable.getLineNumberTable();
        return Arrays.stream(lineNumberArr).map(LineNumber::getStartPC).collect(Collectors.toSet());
    }

}
