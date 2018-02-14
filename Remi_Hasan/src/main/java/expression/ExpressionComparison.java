package expression;

import model.Form;

public abstract class ExpressionComparison extends ExpressionBinary<Boolean>{

    ExpressionComparison(Expression left, Expression right, BinaryOperation op, String opString) {
        super(left, right, op, opString);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }
}