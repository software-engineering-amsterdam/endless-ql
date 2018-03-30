package ql.model.expression.unary;

import ql.model.expression.Expression;
import ql.visitor.IQLVisitor;

public class NegationExpression extends UnaryExpression {

    public NegationExpression(Expression expression) {
        super(expression);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}