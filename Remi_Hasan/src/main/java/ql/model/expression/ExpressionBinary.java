package ql.model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionBinary extends Expression {
    private final Expression left;
    private final Expression right;

    protected ExpressionBinary(Token start, Expression left, Expression right) {
        super(start);
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}