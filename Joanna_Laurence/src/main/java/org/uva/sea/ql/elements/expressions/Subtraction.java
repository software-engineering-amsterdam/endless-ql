package org.uva.sea.ql.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.elements.ASTNode;
import org.uva.sea.ql.elements.types.Type;
import org.uva.sea.ql.nodeTypes.BinaryOperator;
import org.uva.sea.visitor.IASTVisitor;

public class Subtraction extends BinaryOperator {
    public Subtraction(Token token, ASTNode leftHandSide, ASTNode rightHandSide) {
        super(token, leftHandSide, rightHandSide);
    }

    public Type getType() {
        return this.getLeftHandSide().getType();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
