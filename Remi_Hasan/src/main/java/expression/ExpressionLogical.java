package expression;

import model.Form;

public class ExpressionLogical  extends ExpressionBinary<Boolean>{
    ExpressionLogical(Expression left, Expression right, BinaryOperation op, String opString) {
        super(left, right, op, opString);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }
}