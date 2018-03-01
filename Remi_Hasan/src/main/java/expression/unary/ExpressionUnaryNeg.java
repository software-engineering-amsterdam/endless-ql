package expression.unary;

import expression.Expression;
import expression.variable.ExpressionVariable;
import expression.ReturnType;
import expression.binary.ExpressionArithmeticMultiply;
import expression.variable.ExpressionVariableNumber;

import java.math.BigDecimal;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression e) {
        super(e, "-");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariableNumber leftEvaluated = new ExpressionVariableNumber(new BigDecimal(-1));
        Expression rightEvaluated = this.expression.evaluate();
        return new ExpressionArithmeticMultiply(leftEvaluated, rightEvaluated).evaluate();
    }

    @Override
    public ReturnType getReturnType() {
        return this.expression.getReturnType();
    }

    @Override
    public void typeCheck() {
        this.expression.typeCheck();

        if (!this.expression.getReturnType().neg()) {
            throw new IllegalArgumentException("Cannot apply operator - to '" + this.expression.getReturnType() + "'");
        }
    }
}