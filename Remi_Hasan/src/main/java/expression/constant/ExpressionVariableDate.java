package expression.constant;

import expression.ReturnType;

public class ExpressionVariableDate extends ExpressionVariable<String> {
    // TODO: figure out best way to save and/or validate a date

    public ExpressionVariableDate(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.DATE;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = value;
    }

    // TODO
}