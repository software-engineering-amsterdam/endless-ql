package expression;

import model.Form;

public class ExpressionArithmeticDivide extends ExpressionArithmetic {

    public ExpressionArithmeticDivide(Expression left, Expression right) {
        super(left, right, "/");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).divide(this.right.evaluate(form));
    }
}
