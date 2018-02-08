public class ExpressionAnd extends ExpressionBinary<Boolean> {
    
    @Override
    public Boolean evaluate() {
        return this.leftExpression.evaluate() == this.leftExpression.evaluate();
    }
}
