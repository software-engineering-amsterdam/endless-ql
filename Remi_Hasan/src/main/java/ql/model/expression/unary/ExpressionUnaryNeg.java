package ql.model.expression.unary;

import ql.IQLVisitor;
import ql.model.expression.Expression;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Expression expression) {
        super( expression);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}