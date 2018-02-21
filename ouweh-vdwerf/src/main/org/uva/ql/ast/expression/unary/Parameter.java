package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;

public class Parameter extends Expression {
    private final String value;

    public Parameter(String value){
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
