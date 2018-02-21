package expression;

public class ExpressionNeg extends ExpressionUnary<Double> {

    public ExpressionNeg(Expression v) {
        super(v, "-");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = new ExpressionVariableInteger(-1);
        ExpressionVariable rightEvaluated = v.evaluate();
        return new ExpressionArithmeticMultiply(leftEvaluated, rightEvaluated).evaluate();
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.Number;
    }
}