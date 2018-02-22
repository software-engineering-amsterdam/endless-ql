package expression;

public abstract class ExpressionBinary<T> extends Expression<T> {
    final Expression left;
    final Expression right;
    final String opString;

    public ExpressionBinary(Expression left, Expression right, String opString) {
        this.left = left;
        this.right = right;
        this.opString = opString;
    }

    public abstract ReturnType getReturnType();

    @Override
    public String toString() {
        return this.left.toString() + opString + this.right.toString();
    }

    @Override
    public abstract boolean equals(Object other);
}