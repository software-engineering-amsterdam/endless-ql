package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.visitors.ExpressionVisitor;

public class Identifier extends Expression {

    private final String name;

    public Identifier(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
