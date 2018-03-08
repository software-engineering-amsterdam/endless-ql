package expression.unary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionUnary;
import expression.ReturnType;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}