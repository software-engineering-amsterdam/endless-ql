package ql.ast.expressions.literals;

import ql.ast.expressions.Expression;
import ql.visitors.ExpressionVisitor;

public class BooleanLiteral extends Expression {
    private final boolean value;
    public BooleanLiteral(int lineNumber, boolean value) {
        super(lineNumber);
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return null;
    }
}
