package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;

public class Identifier extends Expression {

    private final String name;

    public Identifier(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }
}
