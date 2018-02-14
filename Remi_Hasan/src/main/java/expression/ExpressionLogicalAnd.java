package expression;

import model.Form;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right) {
        super(left, right, "&&");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).divide(this.right.evaluate(form));
    }
}