package expression;

public class ExpressionUnaryNeg extends ExpressionUnary<Double> {

    public ExpressionUnaryNeg(Expression v) {
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
        return v.getReturnType();
    }
}