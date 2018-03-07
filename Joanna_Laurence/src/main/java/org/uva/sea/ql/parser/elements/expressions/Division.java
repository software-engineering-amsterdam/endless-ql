package org.uva.sea.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.nodeTypes.BinaryOperator;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.interpreter.visitor.IASTVisitor;

public class Division extends BinaryOperator {
    public Division(Token token, ASTNode leftHandSide, ASTNode rightHandSide) {
        super(token, leftHandSide, rightHandSide);
    }

    /**
     * The valueTypes is returned, so the type of LHS
     *
     * @return The type
     */
    public Type getType() {
        return super.getLeftHandSide().getType();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
