package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;

public class StringLiteral extends Expression {
    private final String value;

    public StringLiteral(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
