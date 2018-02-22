package expression;

public abstract class ExpressionLogical extends ExpressionBinary<Boolean> {

    public ExpressionLogical(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }
}