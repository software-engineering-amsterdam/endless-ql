package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

public class Int extends ASTNode {
    private int value;

    public Int(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void traverse(Traverse traverse) {
        traverse.doInt(this);
    }

    public Type getType() {
        return new Type("integer");
    }
}
