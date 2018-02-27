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

    public ExpressionVariable divide(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable multiply(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable subtract(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable sum(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable equals(ExpressionVariable other) {
        if (this.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value.equals(other.value));
    }

    public ExpressionVariable ge(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable gt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable le(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable lt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable and(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable or(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable not() {
        return new ExpressionVariableUndefined();
    }
}
