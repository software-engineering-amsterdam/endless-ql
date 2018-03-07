package expression.unary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionUnary;
import expression.ReturnType;

public class ExpressionUnaryNot extends ExpressionUnary<Boolean> {

    public ExpressionUnaryNot(Expression expression) {
        super(expression);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}