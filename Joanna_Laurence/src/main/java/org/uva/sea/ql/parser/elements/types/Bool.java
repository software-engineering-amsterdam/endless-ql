package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;

public class Bool extends ASTNode {
    private boolean value;

    public Bool(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    public Type getExprType() {
        return new Type("boolean");
    }

    public boolean checkType() {
        return true;
    }
}
