package expression.unary;

import expression.Expression;
import expression.constant.ExpressionVariable;
import expression.ReturnType;

public class ExpressionUnaryNot extends ExpressionUnary {

    public ExpressionUnaryNot(Expression e) {
        super(e, "!");
    }

    @Override
    public ExpressionVariable evaluate() {
        return this.expression.evaluate().not();
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }
}