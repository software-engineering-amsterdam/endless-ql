package expression;

import model.Form;

public class ExpressionArithmeticSum extends ExpressionArithmetic{

    public ExpressionArithmeticSum(Expression left, Expression right) {
        super(left, right, "+");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).sum(this.right.evaluate(form));
    }
}