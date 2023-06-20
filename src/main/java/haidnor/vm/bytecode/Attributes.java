package haidnor.vm.bytecode;

import haidnor.vm.bytecode.attribute.Attribute;

import java.util.ArrayList;
import java.util.List;

public class Attributes {

    public final List<Attribute> attributes;

    public Attributes(int attributeCount) {
        this.attributes = new ArrayList<>(attributeCount);
    }

    public void addAttribute(Attribute attribute) {
        this.attributes.add(attribute);
    }

}
