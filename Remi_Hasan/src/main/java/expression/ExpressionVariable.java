package expression;

public abstract class ExpressionVariable<T> extends Expression {
    public final T value;

    public ExpressionVariable(T value) {
        this.value = value;
    }
}
