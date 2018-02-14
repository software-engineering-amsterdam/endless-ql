package expression;

import model.Form;

public class ExpressionVariableDate extends ExpressionVariable<String> {
    // TODO: figure out best way to save and/or validate a date

    public ExpressionVariableDate(String value) {
        this.value = value;
    }

    @Override
    public ReturnType getReturnType(Form form) {
        return ReturnType.Date;
    }

    @Override
    public void setValue(String value){
        this.value = value;
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

    @Override
    public boolean isSetable(Form form){
        return true;
    }
}
