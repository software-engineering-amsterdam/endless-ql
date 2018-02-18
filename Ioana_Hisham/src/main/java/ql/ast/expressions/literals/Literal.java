package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;

public abstract class Literal extends Expression {

    public Literal(int lineNumber) {
        super(lineNumber);
    }
}
