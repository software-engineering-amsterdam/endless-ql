package expression;

public abstract class ExpressionComparison extends ExpressionBinary<Boolean> {

    ExpressionComparison(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Boolean;
    }

    @Override
    public boolean equals(Object other) {
        if(this.getClass().equals(other.getClass())){
            ExpressionComparison otherExpression = (ExpressionComparison) other;
            return this.left.equals(otherExpression.left) && this.right.equals(otherExpression.right);
        }
        return false;
    }
}