package expression;

import model.Form;

public class ExpressionArithmetic extends ExpressionBinary<Double>{
    ExpressionArithmetic(Expression left, Expression right, BinaryOperation op, String opString) {
        super(left, right, op, opString);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }
}
