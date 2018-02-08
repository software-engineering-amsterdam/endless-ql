public abstract class ExpressionBinary<T> extends Expression<T> {
    Expression leftExpression;
    Expression rightExpression;

    public abstract void setLeftExpression(Expression<?> leftExpression);
    public abstract void setRightExpression(Expression<?> rightExpression);
}
