package expression;

public class ExpressionNot extends ExpressionUnary<Boolean> {

    public ExpressionNot(Expression v) {
        super(v, "!");
    }

    @Override
    public ExpressionVariable evaluate() {
        return this.v.evaluate().not();
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }
}