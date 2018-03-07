package expression;

public abstract class ExpressionVariable<T> extends Expression<T> {
    public final T value;

    public ExpressionVariable(T value){
        this.value = value;
    }
}
