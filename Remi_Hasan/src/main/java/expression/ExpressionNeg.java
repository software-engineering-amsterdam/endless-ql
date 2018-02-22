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
        return v.getReturnType();
    }

    @Override
    public boolean equals(Object other) {
        if(this.getClass().equals(other.getClass())){
            ExpressionNeg otherExpression = (ExpressionNeg) v;
            return this.v.equals(otherExpression.v);
        }
        return false;
    }
}