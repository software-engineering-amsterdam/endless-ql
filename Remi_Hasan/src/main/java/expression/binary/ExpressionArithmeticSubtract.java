package expression.binary;

import astvisitor.IASTVisitor;
import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionBinary;

public class ExpressionArithmeticSubtract extends ExpressionBinary<Number> {

    public ExpressionArithmeticSubtract(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
