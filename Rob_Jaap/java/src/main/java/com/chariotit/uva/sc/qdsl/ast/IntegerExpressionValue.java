package com.chariotit.uva.sc.qdsl.ast;

public class IntegerExpressionValue extends NumberExpressionValue {

    private Integer value;

    public IntegerExpressionValue(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    private void validateExpressionValueType(ExpressionValue expressionValue) {
        if (!(expressionValue instanceof IntegerExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }
    }

    @Override
    public IntegerExpressionValue divide(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new IntegerExpressionValue(value / ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue multiply(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new IntegerExpressionValue(value * ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue add(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new IntegerExpressionValue(value + ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue subtract(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new IntegerExpressionValue(value - ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public BooleanExpressionValue greaterThan(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value > ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue greaterThanEquals(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);
        return new BooleanExpressionValue(value >= ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThan(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);
        return new BooleanExpressionValue(value < ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThanEquals(NumberExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);
        return new BooleanExpressionValue(value <= ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value.equals(((IntegerExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {
        return equalTo(expressionValue).not();
    }
}
