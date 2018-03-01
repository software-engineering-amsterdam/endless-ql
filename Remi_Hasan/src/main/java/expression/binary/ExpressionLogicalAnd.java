package expression.binary;

import expression.Expression;
import expression.ReturnType;
import expression.variable.ExpressionVariable;

public class ExpressionLogicalAnd extends ExpressionLogical {

    public ExpressionLogicalAnd(Expression left, Expression right) {
        super(left, right, "&&");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.and(rightEvaluated);
    }

    @Override
    public void typeCheck() {
        left.typeCheck();
        right.typeCheck();

        if(!left.getReturnType().and(right.getReturnType())) {
            throw new IllegalArgumentException("Cannot apply operator && to '"
                    + this.left.getReturnType() + "' and '" + this.right.getReturnType() + "'");

        }
    }
}