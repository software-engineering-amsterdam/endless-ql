package com.chariotit.uva.sc.qdsl.ast;

public class BooleanExpressionValue extends ExpressionValue {

    private Boolean value;

    public BooleanExpressionValue(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    public void setValue(Boolean value) {
        this.value = value;
    }

    public BooleanExpressionValue not() {
        return new BooleanExpressionValue(!value);
    }

    public BooleanExpressionValue and(BooleanExpressionValue expressionValue) {

        return new BooleanExpressionValue(value && expressionValue.getValue());
    }

    public BooleanExpressionValue or(BooleanExpressionValue expressionValue) {

        return new BooleanExpressionValue(value || expressionValue.getValue());
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {

        return new BooleanExpressionValue(value.equals(((BooleanExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {

        return new BooleanExpressionValue(!value.equals(((BooleanExpressionValue)expressionValue)
                .getValue()));
    }
}
