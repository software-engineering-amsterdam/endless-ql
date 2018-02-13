package expression;

import model.Form;

public class ExpressionVariableUndefined extends ExpressionVariable<Object> {
    @Override
    public boolean isEvaluable(Form form) {
        return false;
    }

    @Override
    public Object evaluate(Form form) {
        return this;
    }

    @Override
    public Boolean equals(Form form, Expression other) {
        return null;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Undefined;
    }

    @Override
    public Double divide(Form form, Expression other) {
        return null;
    }

    @Override
    public Double multiply(Form form, Expression other) {
        return null;
    }

    @Override
    public Double subtract(Form form, Expression other) {
        return null;
    }

    @Override
    public Double sum(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean ge(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean gt(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean le(Form form, Expression other) {
        return null;
    }

    @Override
    public Boolean lt(Form form, Expression other) {
        return null;
    }
}
