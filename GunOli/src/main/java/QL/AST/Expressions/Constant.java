package QL.AST.Expressions;

public abstract class Constant<T> extends Expression {
    private T value;

    public Constant(T value, int lineNumber) {
        super(lineNumber);
        this.value = value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return this.value;
    }

    public abstract String toString();
}
