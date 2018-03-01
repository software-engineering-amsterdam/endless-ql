package expression.variable;

import expression.ReturnType;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Boolean value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.BOOLEAN;
    }

    @Override
    public void setValue(String value) {
        this.value = !value.isEmpty() && Boolean.parseBoolean(value);
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value && Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        if (this.value == null || other.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value || Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable not() {
        if (this.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(!this.value);
    }
}