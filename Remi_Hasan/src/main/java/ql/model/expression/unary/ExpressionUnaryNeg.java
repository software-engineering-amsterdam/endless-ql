package ql.model.expression.unary;

import ql.IQLVisitor;

import ql.model.expression.Expression;
import ql.model.expression.ExpressionUnary;
import org.antlr.v4.runtime.Token;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Token start, Expression expression) {
        super(start, expression);
    }

    @Override
    public <T> T accept(IQLVisitor<T> visitor) {
        return visitor.visit(this);
    }
}