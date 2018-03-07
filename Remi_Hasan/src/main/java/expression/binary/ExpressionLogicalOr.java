package expression.binary;

import astvisitor.BoolValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionLogicalOr extends ExpressionBinary<Boolean> {

    protected ExpressionLogicalOr(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BoolValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}