package expression.binary;

import astvisitor.IASTVisitor;
import astvisitor.NumValue;
import expression.Expression;
import expression.ExpressionBinary;
import expression.ReturnType;

public class ExpressionArithmeticMultiply extends ExpressionBinary<Number> {

    public ExpressionArithmeticMultiply(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.NUMBER;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
