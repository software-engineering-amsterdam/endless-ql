package main.org.uva.ql.ast.expression.binary;

import main.org.uva.ql.ast.expression.Expression;

public abstract class BinaryOperation extends Expression {

    private final Expression left;
    private final Expression right;

    public BinaryOperation(Expression left, Expression right){
        this.left = left;
        this.right = right;
    }

    public Expression getLeft() {
        return this.left;
    }

    public Expression getRight() {
        return this.right;
    }
}
