package expression;

public abstract class ExpressionVariable<T> extends Expression<T> {

    T value;

    ExpressionVariable(T value) {
        this.value = value;
    }

    public T get() {
        return this.value;
    }

    @Override
    public ExpressionVariable<T> evaluate() {
        return this;
    }

    @Override
    public boolean isSettable() {
        return true;
    }


    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public abstract ExpressionVariable divide(ExpressionVariable other);

    public abstract ExpressionVariable multiply(ExpressionVariable other);

    public abstract ExpressionVariable subtract(ExpressionVariable other);

    public abstract ExpressionVariable sum(ExpressionVariable other);

    public ExpressionVariable equals(ExpressionVariable other) {
        if (this.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value.equals(other.value));
    }

    public abstract ExpressionVariable ge(ExpressionVariable other);

    public abstract ExpressionVariable gt(ExpressionVariable other);

    public abstract ExpressionVariable le(ExpressionVariable other);

    public abstract ExpressionVariable lt(ExpressionVariable other);

    public abstract ExpressionVariable and(ExpressionVariable other);

    public abstract ExpressionVariable or(ExpressionVariable other);

    public abstract ExpressionVariable not();
}
