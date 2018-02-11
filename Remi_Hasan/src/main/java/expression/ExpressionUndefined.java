package expression;

import model.Form;

public class ExpressionUndefined extends Expression<Object> {
    @Override
    public Object evaluate(Form form) {
        return this;
    }

    @Override
    public boolean isEvaluable(Form form) {
        return false;
    }
}
