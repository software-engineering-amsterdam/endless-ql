package expression;

import model.Form;

public class ExpressionArithmeticSum extends ExpressionArithmetic{

    public ExpressionArithmeticSum(Expression left, Expression right) {
        super(left, right, "+");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        ExpressionVariable leftEvaluated = this.left.evaluate(form);
        ExpressionVariable rightEvaluated = this.right.evaluate(form);
        return leftEvaluated.sum(rightEvaluated);
    }
}