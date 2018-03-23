package ql.model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionBinary extends Expression {
    public final Expression left;
    public final Expression right;

    protected ExpressionBinary(Token start, Expression left, Expression right) {
        super(start);
        this.left = left;
        this.right = right;
    }
}