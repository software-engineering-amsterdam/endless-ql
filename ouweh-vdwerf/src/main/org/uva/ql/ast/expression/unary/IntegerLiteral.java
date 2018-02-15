package main.org.uva.ql.ast.expression.unary;

import main.org.uva.ql.ast.expression.Expression;

public class IntegerLiteral extends Expression {
    private final int value;

    public IntegerLiteral(String value){
        this.value = Integer.parseInt(value);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }
}
