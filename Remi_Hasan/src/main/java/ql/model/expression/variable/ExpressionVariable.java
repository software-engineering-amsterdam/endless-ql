package ql.model.expression.variable;

import org.antlr.v4.runtime.Token;
import ql.model.expression.Expression;

public abstract class ExpressionVariable<T> extends Expression {
    public final T value;

    public ExpressionVariable(Token start, T value) {
        super(start);
        this.value = value;
    }
}
