package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;

public class Neg extends ASTNode {
    private ASTNode value;

    public Neg(ASTNode value) {
        this.value = value;
    }

    public ASTNode getValue() {
        return value;
    }

    public void setValue(ASTNode value) {
        this.value = value;
    }

    public Type getExprType() {
        return value.getExprType();
    }

    public boolean checkType() {
        return value.checkType();
    }
}

