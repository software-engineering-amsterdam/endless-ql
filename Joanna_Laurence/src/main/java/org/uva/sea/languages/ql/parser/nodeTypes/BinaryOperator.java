package org.uva.sea.languages.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class BinaryOperator extends Expression {
    private final Expression leftHandSide;
    private final Expression rightHandSide;

    protected BinaryOperator(final Token token, final Expression leftHandSide, final Expression rightHandSide) {
        super(token);
        this.leftHandSide = leftHandSide;
        this.rightHandSide = rightHandSide;
    }

    public final ASTNode getLeftHandSide() {
        return this.leftHandSide;
    }

    public final ASTNode getRightHandSide() {
        return this.rightHandSide;
    }

    @Override
    public <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
