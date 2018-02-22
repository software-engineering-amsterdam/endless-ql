package expression;

public abstract class ExpressionArithmetic extends ExpressionBinary<Double> {
    ExpressionArithmetic(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        if(left.getReturnType() == ReturnType.Decimal || right.getReturnType() == ReturnType.Decimal)
            return ReturnType.Decimal;
        else
            return ReturnType.Integer;
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
