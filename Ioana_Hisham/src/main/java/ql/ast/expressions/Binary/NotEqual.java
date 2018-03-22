package ql.ast.expressions.binary;

import ql.ast.expressions.Expression;
import ql.visitors.ExpressionVisitor;

public class NotEqual extends Binary {

    public NotEqual(int lineNumber, Expression leftHandSide, Expression rightHandSide) {
        super(lineNumber, leftHandSide, rightHandSide);
    }

    @Override
    public <T> T accept(ExpressionVisitor<T> expressionVisitor) {
        return expressionVisitor.visit(this);
    }
}
