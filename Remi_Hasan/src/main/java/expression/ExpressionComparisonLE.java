package expression;

import model.Form;

public class ExpressionComparisonLE extends ExpressionComparison {

    public ExpressionComparisonLE(Expression left, Expression right) {
        super(left, right, "<=");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).le(this.right.evaluate(form));
    }
}