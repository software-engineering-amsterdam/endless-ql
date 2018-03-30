package ql.model.expression.unary;

import ql.model.expression.Expression;
import ql.visitor.IQLVisitor;

public class NotExpression extends UnaryExpression {

    public NotExpression(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}