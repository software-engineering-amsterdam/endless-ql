package com.chariotit.uva.sc.qdsl.ast;

public class StringExpressionValue extends ExpressionValue {

    private String value;

    public StringExpressionValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private void validateExpressionValueType(ExpressionValue expressionValue) {
        if (!(expressionValue instanceof StringExpressionValue)) {
            throw new RuntimeException("Incompatible expression type");
        }
    }

    @Override
    public BooleanExpressionValue equalTo(ExpressionValue expressionValue) {
        validateExpressionValueType(expressionValue);

        return new BooleanExpressionValue(value.equals(((StringExpressionValue)expressionValue)
                .getValue()));
    }

    @Override
    public BooleanExpressionValue notEqualTo(ExpressionValue expressionValue) {
        return equalTo(expressionValue).not();
    }
}
