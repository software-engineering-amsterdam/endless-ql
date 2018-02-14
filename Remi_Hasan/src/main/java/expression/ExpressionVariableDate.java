package expression;

import model.Form;

public class ExpressionVariableDate extends ExpressionVariable<String> {
    // TODO: figure out best way to save and/or validate a date

    public ExpressionVariableDate(String value) {
        super(value);
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
    public ExpressionVariable divide(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable multiply(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable subtract(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    // TODO
    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        if(this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        if(this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        if(this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
        if(this.value == null || other == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable not() {
        return new ExpressionVariableUndefined();
    }
}
