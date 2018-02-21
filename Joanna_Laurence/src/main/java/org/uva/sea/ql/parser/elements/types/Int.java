package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.TraverseType;
import org.uva.sea.ql.traverse.Traverse;

public class Int extends ASTNode {
    private int value;

    public Int(String value) {
        this.value = Integer.parseInt(value);
    }

    public Int(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doInt(this);
    }

    public Type getType() {
        return new Type("integer");
    }
}
