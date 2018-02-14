package expression;

import model.Form;

public class ExpressionNeg extends ExpressionUnary<Double> {

    public ExpressionNeg(Expression v) {
        super(v, "-");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        ExpressionVariable leftEvaluated = new ExpressionVariableInteger(-1);
        ExpressionVariable rightEvaluated = v.evaluate(form);
        return new ExpressionArithmeticMultiply(leftEvaluated, rightEvaluated).evaluate(form);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }
}