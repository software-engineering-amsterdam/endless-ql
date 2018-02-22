package expression;

public abstract class ExpressionUnary<T> extends Expression<T> {
    final Expression v;
    final String opString;

    public ExpressionUnary(Expression v, String opString) {
        this.v = v;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType();

    @Override
    public String toString() {
        return opString + this.v.toString();
    }

    @Override
    public abstract boolean equals(Object other);
}