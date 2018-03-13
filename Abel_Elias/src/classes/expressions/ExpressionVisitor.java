package classes.expressions;

public interface ExpressionVisitor<T> {
    public T visit(BooleanLiteral node);
}

