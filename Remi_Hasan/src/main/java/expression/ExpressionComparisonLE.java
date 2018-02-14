package expression;

import model.Form;

public class ExpressionComparisonLE extends ExpressionComparison {

    public ExpressionComparisonLE(Expression left, Expression right) {
        super(left, right, "<=");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        ExpressionVariable leftEvaluated = this.left.evaluate(form);
        ExpressionVariable rightEvaluated = this.right.evaluate(form);
        return leftEvaluated.le(rightEvaluated);
    }
}