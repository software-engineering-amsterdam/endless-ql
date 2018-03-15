package com.chariotit.uva.sc.qdsl.qls.ast.node.widget;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.qls.ast.node.WidgetType;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class SpinboxWidget extends WidgetType {

    public SpinboxWidget(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.INTEGER;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
