package expression;

import model.Form;

public class ExpressionLogicalOr extends ExpressionLogical {

    public ExpressionLogicalOr(Expression left, Expression right) {
        super(left, right, "||");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.left.evaluate(form).or(this.right.evaluate(form));
    }
}