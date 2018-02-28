package expression.binary;

import expression.Expression;
import expression.ReturnType;

public abstract class ExpressionArithmetic extends ExpressionBinary {
    ExpressionArithmetic(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType() {
        if(left.getReturnType() == ReturnType.DECIMAL || right.getReturnType() == ReturnType.DECIMAL)
            return ReturnType.DECIMAL;
        else
            return ReturnType.INTEGER;
    }
}
