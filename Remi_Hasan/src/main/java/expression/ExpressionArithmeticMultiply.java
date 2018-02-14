package expression;

import model.Form;

public class ExpressionArithmeticMultiply extends ExpressionArithmetic {

    public ExpressionArithmeticMultiply(Expression left, Expression right) {
        super(left, right, "*");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).multiply(this.right.evaluate(form));
    }
}
