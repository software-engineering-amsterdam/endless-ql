package expression;

import model.Form;

public class ExpressionComparisonGT extends ExpressionComparison{

    public ExpressionComparisonGT(Expression left, Expression right) {
        super(left, right, ">");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        ExpressionVariable leftEvaluated = this.left.evaluate(form);
        ExpressionVariable rightEvaluated = this.right.evaluate(form);
        return leftEvaluated.gt(rightEvaluated);
    }
}
