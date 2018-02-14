package expression;

import model.Form;

public class ExpressionComparisonGE extends ExpressionComparison {

    public ExpressionComparisonGE(Expression left, Expression right) {
        super(left, right, ">=");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).ge(this.right.evaluate(form));
    }
}
