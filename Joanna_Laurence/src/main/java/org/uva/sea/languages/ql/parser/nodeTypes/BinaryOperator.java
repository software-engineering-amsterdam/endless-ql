package org.uva.sea.languages.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class BinaryOperator extends Expression {
    private final Expression leftHandSide;
    private final Expression rightHandSide;

    protected BinaryOperator(Token token, Expression leftHandSide, Expression rightHandSide) {
        super(token);
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public ASTNode getLeftHandSide() {
        return this.leftHandSide;
    }

    public ASTNode getRightHandSide() {
        return this.rightHandSide;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
