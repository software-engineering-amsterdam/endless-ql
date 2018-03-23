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

    @Override
    public IntegerExpressionValue divide(NumberExpressionValue expressionValue) {

        return new IntegerExpressionValue(value / ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue multiply(NumberExpressionValue expressionValue) {

        return new IntegerExpressionValue(value * ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue add(NumberExpressionValue expressionValue) {

        return new IntegerExpressionValue(value + ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public IntegerExpressionValue subtract(NumberExpressionValue expressionValue) {

        return new IntegerExpressionValue(value - ((IntegerExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public BooleanExpressionValue greaterThan(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value > ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue greaterThanEquals(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value >= ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThan(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value < ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThanEquals(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value <= ((IntegerExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {

        return new BooleanExpressionValue(value.equals(((IntegerExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {
        return equalTo(expressionValue).not();
    }
}
