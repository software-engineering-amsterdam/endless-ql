package org.uva.forcepushql.ast;

public abstract class BinaryExpression extends Expression{

    public Expression left;
    public Expression right;

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }
}
