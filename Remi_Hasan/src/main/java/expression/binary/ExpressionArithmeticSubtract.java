package expression.binary;

import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionArithmeticSubtract extends ExpressionBinary<Number> {

    protected ExpressionArithmeticSubtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public NumValue accept(BaseASTVisitor visitor) {
        return visitor.visit(this);
    }
}
