package expression.unary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionUnary;

public class ExpressionUnaryNot extends ExpressionUnary<Boolean> {

    public ExpressionUnaryNot(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}