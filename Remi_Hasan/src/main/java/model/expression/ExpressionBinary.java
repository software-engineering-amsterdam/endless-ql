package model.expression;

import org.antlr.v4.runtime.Token;

public abstract class ExpressionBinary<T> extends Expression {
    public final Expression left;
    public final Expression right;

    protected ExpressionBinary(Token start, Expression left, Expression right) {
        super(start);
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object other) {
        if(other instanceof ExpressionBinary){
            ExpressionBinary otherExpressionBinary = (ExpressionBinary) other;
            return this.left.equals(otherExpressionBinary.left) && this.right.equals(otherExpressionBinary.right);
        }
        return false;
    }
}