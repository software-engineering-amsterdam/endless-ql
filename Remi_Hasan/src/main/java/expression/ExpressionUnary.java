package expression;

public abstract class ExpressionUnary<T> extends Expression<T> {
    public final Expression value;

    public ExpressionUnary(Expression expression) {
        this.value = expression;
    }

}