package models.ast.elements.expressions.unary.values;

import models.ast.elements.expressions.unary.UnaryExpression;

public class VariableReference extends UnaryExpression {

    private String name;

    public VariableReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
