package expression;

public abstract class ExpressionBinary<T> extends Expression<T> {
    public final Expression left;
    public final Expression right;

    protected ExpressionBinary(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
}