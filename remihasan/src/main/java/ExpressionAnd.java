public class ExpressionAnd extends ExpressionBinary<Boolean> {

    private Expression<Boolean> leftExpression;
    private Expression<Boolean> rightExpression;
    
    @Override
    public Boolean evaluate() {
        return this.leftExpression.evaluate() && this.leftExpression.evaluate();
    }

    @Override
    public Class resultType() {
        return Boolean.class;
    }

    @Override
    public void setLeftExpression(Expression<?> leftExpression) {
        if(leftExpression.resultType() == Boolean.class){
            this.leftExpression = (Expression<Boolean>) leftExpression;
        }
    }

    @Override
    public void setRightExpression(Expression<?> rightExpression) {
        if(rightExpression.resultType() == Boolean.class){
            this.rightExpression = (Expression<Boolean>) rightExpression;
        }
    }
}