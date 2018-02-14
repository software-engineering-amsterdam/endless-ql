package expression;

import model.Form;

public abstract class ExpressionArithmetic extends ExpressionBinary<Double>{
    ExpressionArithmetic(Expression left, Expression right, String opString) {
        super(left, right, opString);
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Number;
    }
}
