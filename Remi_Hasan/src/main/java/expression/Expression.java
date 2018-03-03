package expression;

import analysis.SymbolTable;
import expression.variable.ExpressionVariable;

public abstract class Expression {

    public abstract ReturnType getReturnType(SymbolTable symbolTable);

    public abstract ExpressionVariable evaluate(SymbolTable symbolTable);

    public void setValue(String value) {
        throw new UnsupportedOperationException("Cannot set value to non-variable expression");
    }

    public abstract void typeCheck(SymbolTable symbolTable);

    @Override
    public boolean equals(Object other){
        return this.toString().equals(other.toString());
    }
}