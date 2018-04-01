package com.chariotit.uva.sc.qdsl.ast.ql.type;

public class MoneyExpressionValue extends NumberExpressionValue {

    private Float value = (float) 12.34;

    public MoneyExpressionValue(Float value) { this.value = value; }

    public Float getValue() {  return value;  }

    public void setValue(Float value) {
        this.value = value;
    }

    @Override
    public MoneyExpressionValue divide(NumberExpressionValue expressionValue) {

        return new MoneyExpressionValue(value / ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public NumberExpressionValue multiply(NumberExpressionValue expressionValue) {

        return new MoneyExpressionValue(value * ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public MoneyExpressionValue add(NumberExpressionValue expressionValue) {

        return new MoneyExpressionValue(value + ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public MoneyExpressionValue subtract(NumberExpressionValue expressionValue) {

        return new MoneyExpressionValue(value - ((MoneyExpressionValue)
                expressionValue).getValue());
    }

    @Override
    public BooleanExpressionValue greaterThan(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value > ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue greaterThanEquals(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value >= ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThan(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value < ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue lessThanEquals(NumberExpressionValue expressionValue) {

        return new BooleanExpressionValue(value <= ((MoneyExpressionValue)expressionValue)
                .getValue());
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {

        return new BooleanExpressionValue(value.equals(((MoneyExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {
        return equalTo(expressionValue).not();
    }
}
