package expression;

import model.Form;

public class ExpressionNot extends ExpressionUnary<Boolean> {

    public ExpressionNot(Expression v) {
        super(v, "!");
    }

    @Override
    public ExpressionVariable evaluate(Form form) {
        return this.v.evaluate(form).not();
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Boolean;
    }
}