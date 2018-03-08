package model.expression.unary;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionUnary;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}