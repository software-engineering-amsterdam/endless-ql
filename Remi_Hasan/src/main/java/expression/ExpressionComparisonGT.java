package expression;

import model.Form;

public class ExpressionComparisonGT extends ExpressionComparison{

    public ExpressionComparisonGT(Expression left, Expression right) {
        super(left, right, ">");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).gt(this.right.evaluate(form));
    }
}
