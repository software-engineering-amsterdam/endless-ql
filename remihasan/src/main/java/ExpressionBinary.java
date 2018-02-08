public abstract class ExpressionBinary<T> extends Expression<T> {
    Expression<T> leftExpression;
    Expression<T> rightExpression;

    private void setLeftExpression(Expression<T> leftExpression){
        this.leftExpression = leftExpression;
    }

    private void setRightExpression(Expression<T> rightExpression){
        this.rightExpression = rightExpression;
    }
}
