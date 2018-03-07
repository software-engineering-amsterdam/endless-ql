package expression.binary;

import astvisitor.BaseASTVisitor;
import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionArithmeticSum extends ExpressionBinary<NumValue> {

    protected ExpressionArithmeticSum(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public NumValue accept(BaseASTVisitor<NumValue> visitor) {
        return visitor.visit(this);
    }
}