package models.ast.elements.expressions.unary.values;

import models.ast.elements.expressions.unary.UnaryExpression;

public class SingleValue extends UnaryExpression {

    private String value;

    public SingleValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
