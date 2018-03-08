package model.expression.unary;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionUnary;
import org.antlr.v4.runtime.Token;

public class ExpressionUnaryNot extends ExpressionUnary<Boolean> {

    public ExpressionUnaryNot(Token start, Expression expression) {
        super(start, expression);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}