package ql.model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionVariable<T> extends Expression {
    public final T value;

    public ExpressionVariable(Token start, T value) {
        super(start);
        this.value = value;
    }
}
