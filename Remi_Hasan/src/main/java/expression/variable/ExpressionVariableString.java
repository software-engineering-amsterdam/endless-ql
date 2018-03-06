package expression.variable;

import analysis.SymbolTable;
import expression.ReturnType;

public class ExpressionVariableString extends ExpressionVariable<String> {

    public ExpressionVariableString(String value) {
        super(value);
    }

    @Override
    public ReturnType getReturnType(SymbolTable symbolTable) {
        return ReturnType.STRING;
    }

    @Override
    public void setValue(String value) {
        if(value.isEmpty())
            this.value = null;
        else
            this.value = value;
    }

    @Override
    public String getStringValue() {
        return this.value;
    }

    @Override
    public ExpressionVariable sum(ExpressionVariable other) {
        return new ExpressionVariableString(this.value + other.value);
    }
}