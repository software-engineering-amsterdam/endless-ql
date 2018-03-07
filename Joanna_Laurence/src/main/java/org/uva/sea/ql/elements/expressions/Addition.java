package org.uva.sea.ql.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.elements.ASTNode;
import org.uva.sea.ql.elements.types.Type;
import org.uva.sea.ql.nodeTypes.BinaryOperator;
import org.uva.sea.visitor.IASTVisitor;

public class Addition extends BinaryOperator {
    public Addition(Token token, ASTNode leftHandSide, ASTNode rightHandSide) {
        super(token, leftHandSide, rightHandSide);
    }

    /**
     * The valueTypes is returned, so the type of LHS
     *
     * @return The type
     */
    public Type getType() {
        return this.getLeftHandSide().getType();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
