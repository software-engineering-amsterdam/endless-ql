package expression.variable;

import analysis.SymbolTable;
import expression.ReturnType;

public class ExpressionVariableBoolean extends ExpressionVariable<Boolean> {

    public ExpressionVariableBoolean(Boolean value) {
        super(value);
    }

    @Override
    public Boolean getBooleanValue() {
        return this.value;
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return ReturnType.BOOLEAN;
    }

    @Override
    public void setValue(String value) {
        this.value = !value.isEmpty() && Boolean.parseBoolean(value);
    }

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        if (this.isUndefined() || other.isUndefined())
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value && Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        if (this.isUndefined() || other.isUndefined())
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value || Boolean.parseBoolean(other.value.toString()));
    }

    @Override
    public ExpressionVariable not() {
        if (this.isUndefined())
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(!this.value);
    }
}