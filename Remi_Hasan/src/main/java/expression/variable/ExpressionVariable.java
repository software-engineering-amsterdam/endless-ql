package expression.variable;

import analysis.SymbolTable;
import expression.Expression;

import java.math.BigDecimal;

public abstract class ExpressionVariable<T> extends Expression {

    T value;

    ExpressionVariable() {
        this.value = null;
    }

    ExpressionVariable(T value) {
        this.value = value;
    }

    public Boolean getBooleanValue() {
        throw new UnsupportedOperationException("Cannot get boolean value");
    }

    public Integer getIntValue() {
        throw new UnsupportedOperationException("Cannot get int value");
    }

    public Double getDecimalValue() {
        throw new UnsupportedOperationException("Cannot get decimal value");
    }

    public BigDecimal getMoneyValue() {
        throw new UnsupportedOperationException("Cannot get money value");
    }

    public String getStringValue() {
        throw new UnsupportedOperationException("Cannot get string value");
    }

    // TODO: getDateValue

    @Override
    public ExpressionVariable evaluate(SymbolTable symbolTable) {
        return this;
    }

    @Override
    public boolean isSettable() {
        return true;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public void typeCheck(SymbolTable symbolTable) { }

    public ExpressionVariable divide(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable multiply(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable subtract(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable sum(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable equals(ExpressionVariable other) {
        if (this.value == null)
            return new ExpressionVariableUndefined();
        return new ExpressionVariableBoolean(this.value.equals(other.value));
    }

    public ExpressionVariable ge(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable gt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable le(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable lt(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable and(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable or(ExpressionVariable other) {
        return new ExpressionVariableUndefined();
    }

    public ExpressionVariable not() {
        return new ExpressionVariableUndefined();
    }
}
