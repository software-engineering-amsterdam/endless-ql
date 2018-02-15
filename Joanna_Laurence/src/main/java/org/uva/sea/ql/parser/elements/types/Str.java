package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

public class Str extends ASTNode {
    private String value;

    public Str(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void traverse(Traverse traverse) {
        traverse.doStr(this);
    }

    public Type getType() {
        return new Type("string");
    }
}
