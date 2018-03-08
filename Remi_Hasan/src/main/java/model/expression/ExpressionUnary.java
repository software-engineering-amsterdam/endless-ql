package model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionUnary<T> extends Expression {
    public final Expression value;

    public ExpressionUnary(Token start, Expression expression) {
        super(start);
        this.value = expression;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ExpressionUnary){
            ExpressionUnary otherExpressionBinary = (ExpressionUnary) other;
            return this.value.equals(otherExpressionBinary.value);
        }
        return false;
    }

}