package model.expression;

public abstract class ExpressionUnary<T> extends Expression {
    public final Expression value;

    public ExpressionUnary(Expression expression) {
        this.value = expression;
    }

}