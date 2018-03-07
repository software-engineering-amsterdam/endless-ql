package expression.binary;

import astvisitor.BaseASTVisitor;
import astvisitor.BoolValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionComparisonEq extends ExpressionBinary<BoolValue> {

    protected ExpressionComparisonEq(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public BoolValue accept(BaseASTVisitor<BoolValue> visitor) {
        return visitor.visit(this);
    }
}
