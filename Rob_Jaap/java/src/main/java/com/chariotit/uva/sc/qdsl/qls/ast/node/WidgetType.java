package com.chariotit.uva.sc.qdsl.qls.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;

public abstract class WidgetType extends AstNode {

    public WidgetType(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public abstract boolean isValidExpressionType(ExpressionType expressionType);
}
