package expression;

import model.Form;

public class ExpressionArithmeticSubtract extends ExpressionArithmetic {

    public ExpressionArithmeticSubtract(Expression left, Expression right) {
        super(left, right, "-");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).subtract(this.right.evaluate(form));
    }
}
