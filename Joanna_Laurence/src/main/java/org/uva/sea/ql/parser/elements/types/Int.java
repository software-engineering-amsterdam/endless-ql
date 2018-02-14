package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;

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

    public Type getExprType() {
        return new Type("integer");
    }

    public boolean checkType() {
        return true;
    }
}
