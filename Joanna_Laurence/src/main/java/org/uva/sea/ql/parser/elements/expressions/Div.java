package org.uva.sea.ql.parser.elements.expressions;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.parser.nodeTypes.DualNode;
import org.uva.sea.ql.traverse.Traverse;

public class Div extends DualNode {
    public Div(ASTNode lhs, ASTNode rhs) {
        super(lhs, rhs);
    }

    public void traverse(Traverse traverse) {
        super.traverse(traverse);
        traverse.doOperation(this);
        traverse.doDiv(this);
        this.traverseChildren(traverse);
    }

    /**
     * The value is returned, so the type of LHS
     * @return The type
     */
    public Type getType() {
        return this.getLhs().getType();
    }

    public Type getExprType() {
        return lhs.getExprType();
    }

    public boolean checkType() {
        return lhs.getExprType().equals(rhs.getExprType());
    }
}
