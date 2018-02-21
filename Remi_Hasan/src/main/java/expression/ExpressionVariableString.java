<<<<<<< HEAD
package expression;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.String;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
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
        return new ExpressionVariableString(this.value + other.value);
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
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
=======
package expression;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType() {
        return ReturnType.String;
    }

    @Override
    public void setValue(String value) {
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
        return new ExpressionVariableString(this.value + other.value);
    }

    @Override
    public ExpressionVariable ge(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable gt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable le(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    @Override
    public ExpressionVariable lt(ExpressionVariable other) {
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
>>>>>>> 3c171d077d7945c6cc73b62beb833d1ee457800c
