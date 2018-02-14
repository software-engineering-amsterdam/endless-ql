package expression;

import model.Form;

public class ExpressionNeg extends ExpressionUnary<Double> {

    public ExpressionNeg(Expression v) {
        super(v, "!");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return new ExpressionArithmeticMultiply(new ExpressionVariableInteger(-1), v).evaluate(form);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }
}