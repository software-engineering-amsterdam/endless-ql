package expression;

public abstract class ExpressionArithmetic extends ExpressionBinary<Double> {
    ExpressionArithmetic(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Number;
    }
}
