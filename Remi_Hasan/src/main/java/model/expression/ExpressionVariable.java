package model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionVariable<T> extends Expression {
    public final T value;

    public ExpressionVariable(Token start, T value) {
        super(start);
        this.value = value;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof ExpressionVariable){
            ExpressionVariable otherExpressionVariable = (ExpressionVariable) other;
            return this.value.equals(otherExpressionVariable.value);
        }
        return false;
    }
}
