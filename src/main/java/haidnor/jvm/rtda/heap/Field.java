package haidnor.jvm.rtda.heap;

import haidnor.jvm.rtda.UnionSlot;

public class Field {

    public final int accessFlags;

    public final String name;

    public final String descriptor;

    public UnionSlot val;

    public Field(int accessFlags, String name, String descriptor) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
    }

    public Field(int accessFlags, String name, String descriptor, UnionSlot val) {
        this.accessFlags = accessFlags;
        this.name = name;
        this.descriptor = descriptor;
        this.val = val;
    }

    public boolean isStatic() {
        return (accessFlags & 0x0008) != 0;
    }
}
