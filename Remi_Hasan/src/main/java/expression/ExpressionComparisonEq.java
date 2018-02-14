package expression;

import model.Form;

public class ExpressionComparisonEq extends ExpressionComparison {


    public ExpressionComparisonEq(Expression left, Expression right) {
        super(left, right, "==");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        ExpressionVariable leftEvaluated = this.left.evaluate(form);
        ExpressionVariable rightEvaluated = this.right.evaluate(form);
        return leftEvaluated.equals(rightEvaluated);
    }
}
