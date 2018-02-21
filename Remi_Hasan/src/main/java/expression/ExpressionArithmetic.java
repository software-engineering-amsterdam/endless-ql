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
}
