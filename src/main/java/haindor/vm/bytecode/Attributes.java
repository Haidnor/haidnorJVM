package haindor.vm.bytecode;

import haindor.vm.bytecode.attribute.Attribute;

public class Attributes {

    public final Attribute[] attributes;

    public Attributes(int size) {
        this.attributes = new Attribute[size];
    }
}
