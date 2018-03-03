package expression.variable;

import analysis.SymbolTable;
import expression.ReturnType;

public class ExpressionVariableUndefined extends ExpressionVariable<Object> {

    private ReturnType returnType = ReturnType.UNDEFINED;

    public ExpressionVariableUndefined() {
        super(false);
    }

    public ExpressionVariableUndefined(ReturnType type) {
        super(null);
        this.setReturnType(type);
    }

    private void setReturnType(ReturnType type) {
        switch (type) {
            case INTEGER:
            case DECIMAL:
            case MONEY:
                this.returnType = ReturnType.NUMBER;
                break;
            default:
                this.returnType = type;
        }
    }

    @Override
    public boolean isUndefined() {
        return true;
    }

    @Override
    public ExpressionVariable evaluate(SymbolTable symbolTable) {
        return this;
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return this.returnType;
    }

    @Override
    public void setValue(String value) {
        throw new UnsupportedOperationException("Cannot set undefined variable");
    }

    @Override
    public Boolean getBooleanValue() {
        return false;
    }

    // Undefined should be treated as 'false' in boolean cases, so implement boolean functions

    @Override
    public ExpressionVariable and(ExpressionVariable other) {
        return new ExpressionVariableBoolean(false);
    }

    @Override
    public ExpressionVariable or(ExpressionVariable other) {
        if (other.isUndefined())
            return new ExpressionVariableBoolean(false);
        return new ExpressionVariableBoolean(other.getBooleanValue());
    }

    @Override
    public ExpressionVariable not() {
        return new ExpressionVariableBoolean(true);
    }
}