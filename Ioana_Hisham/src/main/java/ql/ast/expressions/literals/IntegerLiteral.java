package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.visitors.ExpressionVisitor;

public class IntegerLiteral extends Expression {
    private final int value;
    public IntegerLiteral(int lineNumber, int value) {
        super(lineNumber);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
