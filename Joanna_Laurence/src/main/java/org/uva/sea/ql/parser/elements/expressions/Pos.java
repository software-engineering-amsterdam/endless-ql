package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;

public class Pos extends ASTNode {
    private ASTNode value;

    public Pos(ASTNode value) {
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
