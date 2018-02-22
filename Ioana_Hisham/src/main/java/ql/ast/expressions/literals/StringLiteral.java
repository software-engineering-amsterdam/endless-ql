package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;

public class StringLiteral extends Expression {

    private final String value;

    public StringLiteral(int lineNumber, String value) {
        super(lineNumber);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
