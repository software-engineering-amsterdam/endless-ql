package expression;

public class ExpressionUnaryNot extends ExpressionUnary<Boolean> {

    public ExpressionUnaryNot(Expression v) {
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