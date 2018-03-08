package model.expression.unary;

import evaluation.IASTVisitor;
import model.expression.Expression;
import model.expression.ExpressionUnary;
import org.antlr.v4.runtime.Token;

public class ExpressionUnaryNeg extends ExpressionUnary {

    public ExpressionUnaryNeg(Token start, Expression expression) {
        super(start, expression);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean equals(Object other) {
        return super.equals(other) && other instanceof ExpressionUnaryNeg;
    }
}