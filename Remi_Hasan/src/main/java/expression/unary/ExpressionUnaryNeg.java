package expression.unary;

import astvisitor.BaseASTVisitor;
import expression.Expression;
import expression.ExpressionUnary;

public class ExpressionUnaryNeg extends ExpressionUnary {

    ExpressionUnaryNeg(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(BaseASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}