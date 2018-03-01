package expression.binary;

import expression.Expression;
import expression.variable.ExpressionVariable;

public class ExpressionArithmeticSubtract extends ExpressionArithmetic {

    public ExpressionArithmeticSubtract(Expression left, Expression right) {
        super(left, right, "-");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariable leftEvaluated = this.left.evaluate();
        ExpressionVariable rightEvaluated = this.right.evaluate();
        return leftEvaluated.subtract(rightEvaluated);
    }

    @Override
    public void typeCheck() {
        this.left.typeCheck();
        this.right.typeCheck();

        if (!this.left.getReturnType().subtract(this.right.getReturnType())) {
            throw new IllegalArgumentException("Cannot apply operator - to '"
                    + this.left.getReturnType() + "' and '" + this.right.getReturnType() + "'");
        }
    }
}
