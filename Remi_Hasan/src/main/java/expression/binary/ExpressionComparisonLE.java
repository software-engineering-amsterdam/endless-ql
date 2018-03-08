package expression.binary;

import astvisitor.BoolValue;
import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;
import expression.ReturnType;

public class ExpressionComparisonLE extends ExpressionBinary<Boolean> {

    public ExpressionComparisonLE(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}