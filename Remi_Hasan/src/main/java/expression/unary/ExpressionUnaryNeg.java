package expression.unary;

import expression.Expression;
import expression.constant.ExpressionVariable;
import expression.constant.ExpressionVariableInteger;
import expression.ReturnType;
import expression.binary.ExpressionArithmeticMultiply;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression e) {
        super(e, "-");
    }

    @Override
    public ExpressionVariable evaluate() {
        ExpressionVariableInteger leftEvaluated = new ExpressionVariableInteger(-1);
        Expression rightEvaluated = this.expression.evaluate();
        return new ExpressionArithmeticMultiply(leftEvaluated, rightEvaluated).evaluate();
    }

    @Override
    public ReturnType getReturnType() {
        return this.expression.getReturnType();
    }
}