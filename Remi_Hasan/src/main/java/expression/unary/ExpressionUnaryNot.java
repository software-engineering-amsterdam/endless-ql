package expression.unary;

import expression.Expression;
import expression.variable.ExpressionVariable;
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

    @Override
    public void typeCheck() {
        this.expression.typeCheck();

        if (!this.expression.getReturnType().not()) {
            throw new IllegalArgumentException("Cannot apply operator ! to '" + this.expression.getReturnType() + "'");
        }
    }
}