package expression.binary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionArithmeticSum extends ExpressionBinary<Number> {

    public ExpressionArithmeticSum(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}