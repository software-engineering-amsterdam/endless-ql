package expression.unary;

import astvisitor.BoolValue;
import expression.Expression;
import expression.ExpressionUnary;

public class ExpressionUnaryNot extends ExpressionUnary<Boolean> {

    ExpressionUnaryNot(Expression expression) {
        super(expression);
    }

    @Override
    public BoolValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}