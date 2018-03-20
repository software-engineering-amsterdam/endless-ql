package ql.ast.expressions;

import ql.ast.Node;
import ql.visitors.ExpressionVisitor;

public abstract class Expression extends Node {

    public Expression(int lineNumber) {
        super(lineNumber);
    }

    public abstract <T> T accept(ExpressionVisitor<T> expressionVisitor);
}
