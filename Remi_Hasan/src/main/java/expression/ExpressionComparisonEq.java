package expression;

import model.Form;

public class ExpressionComparisonEq extends ExpressionComparison {


    public ExpressionComparisonEq(Expression left, Expression right) {
        super(left, right, "==");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).equals(this.right.evaluate(form));
    }
}
