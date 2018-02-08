package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;

public class Identifier {

    private final String name;

    public Identifier(int lineNumber, String name) {
        this.name = name;
    }
}
