package expression.binary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionComparisonGT extends ExpressionBinary<Boolean> {

    public ExpressionComparisonGT(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
