package expression.unary;

import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionUnary;

public class ExpressionUnaryNeg extends ExpressionUnary {

    ExpressionUnaryNeg(Expression expression) {
        super(expression);
    }

    @Override
    public NumValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}