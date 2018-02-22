package expression;

public abstract class ExpressionArithmetic extends ExpressionBinary<Double> {
    ExpressionArithmetic(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        if(left.getReturnType() == ReturnType.DECIMAL || right.getReturnType() == ReturnType.DECIMAL)
            return ReturnType.DECIMAL;
        else
            return ReturnType.INTEGER;
    }

    @Override
    public boolean equals(Object other) {
        if(this.getClass().equals(other.getClass())){
            ExpressionArithmetic otherExpression = (ExpressionArithmetic) other;
            return this.left.equals(otherExpression.left) && this.right.equals(otherExpression.right);
        }
        return false;
    }
}
