package ql.model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionUnary<T> extends Expression {
    public final Expression value;

    public ExpressionUnary(Token start, Expression expression) {
        super(start);
        this.value = expression;
    }
}