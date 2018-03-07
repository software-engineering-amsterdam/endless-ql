package expression.binary;

import astvisitor.IASTVisitor;
import expression.Expression;
import expression.ExpressionBinary;
import expression.ReturnType;

public class ExpressionArithmeticDivide extends ExpressionBinary<Number> {

    public ExpressionArithmeticDivide(Expression left, Expression right){
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
