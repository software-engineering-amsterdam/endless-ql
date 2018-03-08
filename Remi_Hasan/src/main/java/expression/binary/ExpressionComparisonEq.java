package expression.binary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionComparisonEq extends ExpressionBinary<Boolean> {

    public ExpressionComparisonEq(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
