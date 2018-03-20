package com.chariotit.uva.sc.qdsl.ast;

public class MoneyExpressionValue extends NumberExpressionValue {

    private Float value;

    public MoneyExpressionValue(Float value) {
        this.value = value;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    private void validateExpressionValueType(ExpressionValue expressionValue) {
        if (!(expressionValue instanceof MoneyExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }
    }

    @Override
    public MoneyExpressionValue divide(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new MoneyExpressionValue(value / ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public NumberExpressionValue multiply(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new MoneyExpressionValue(value * ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public MoneyExpressionValue add(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new MoneyExpressionValue(value + ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public MoneyExpressionValue subtract(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new MoneyExpressionValue(value - ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public BooleanExpressionValue greaterThan(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value > ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue greaterThanEquals(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value >= ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThan(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value < ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThanEquals(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value <= ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value.equals(((MoneyExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {
        return equalTo(expressionValue).not();
    }
}
