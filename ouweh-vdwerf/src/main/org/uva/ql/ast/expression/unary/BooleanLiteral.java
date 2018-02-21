package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;

public class BooleanLiteral extends Expression {
    private final Boolean value;

    public BooleanLiteral (String value){
        this.value = Boolean.parseBoolean(value);
    }

    @Override
    public String toString() {
        return this.value.toString();
    }
}
