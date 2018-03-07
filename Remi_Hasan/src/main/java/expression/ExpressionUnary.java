package expression;

public abstract class ExpressionUnary<T> extends Expression<T> {
    public final Expression expression;

    ExpressionUnary(Expression expression) {
        this.expression = expression;
    }

}