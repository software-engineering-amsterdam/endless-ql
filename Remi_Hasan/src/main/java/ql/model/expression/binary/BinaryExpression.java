package ql.model.expression.binary;

import ql.model.expression.Expression;

public abstract class BinaryExpression extends Expression {
    private final Expression left;
    private final Expression right;

    protected BinaryExpression(Expression left, Expression right) {
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