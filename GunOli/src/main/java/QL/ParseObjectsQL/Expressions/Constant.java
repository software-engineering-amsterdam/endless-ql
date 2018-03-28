package QL.ParseObjectsQL.Expressions;

public abstract class Constant<T> extends Expression {
    private T value;

    public Constant(T value, int lineNumber) {
        super(lineNumber);
        setValue(value);
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    @Override
    public Constant<T> evaluate() {
        return this;
    }

    public abstract String toString();
}
