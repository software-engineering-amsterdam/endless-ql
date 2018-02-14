package expression;

import model.Form;

public class ExpressionNeg extends ExpressionUnary<Double> {

    public ExpressionNeg(Expression v) {
        super(v, (form, a) -> new ExpressionArithmeticMultiply(new ExpressionVariableInteger(-1), v).evaluate(form), "-");
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }
}
//public class ExpressionNeg extends Expression<Double> {
//    private final Expression value;
//
//    public ExpressionNeg(Expression value) {
//        this.value = value;
//    }
//
//    @Override
//    public ReturnType getReturnType(Form form) {
//        return ReturnType.Number;
//    }
//
//    @Override
//    public ExpressionVariable evaluate(Form form) {
//        ExpressionArithmeticMultiply rewrittenExpression = new ExpressionArithmeticMultiply(new ExpressionVariableDecimal(-1.0), this.value);
//        return rewrittenExpression.evaluate(form);
//    }
//}