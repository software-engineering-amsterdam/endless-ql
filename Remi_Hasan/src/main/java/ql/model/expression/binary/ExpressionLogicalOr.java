package ql.model.expression.binary;

import ql.evaluation.IExpressionVisitor;
import ql.model.expression.Expression;
import ql.model.expression.ExpressionBinary;
import org.antlr.v4.runtime.Token;

public class ExpressionLogicalOr extends ExpressionBinary<Boolean> {

    public ExpressionLogicalOr(Token start, Expression left, Expression right) {
        super(start, left, right);
    }

    @Override
    public <T> T accept(IExpressionVisitor<T> visitor) {
        return visitor.visit(this);
    }
}