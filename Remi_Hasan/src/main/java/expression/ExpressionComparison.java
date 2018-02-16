package expression;

public abstract class ExpressionComparison extends ExpressionBinary<Boolean> {

    ExpressionComparison(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Boolean;
    }
}