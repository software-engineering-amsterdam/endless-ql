package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;

public abstract class WidgetType extends AstNode {

    public WidgetType(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public abstract boolean isValidExpressionType(ExpressionType expressionType);
}
