package expression.binary;

import astvisitor.BoolValue;
import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;
import expression.ReturnType;

public class ExpressionComparisonGT extends ExpressionBinary<Boolean> {

    public ExpressionComparisonGT(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
