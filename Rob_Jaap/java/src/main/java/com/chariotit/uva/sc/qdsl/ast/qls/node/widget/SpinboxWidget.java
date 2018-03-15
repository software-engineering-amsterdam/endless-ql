package com.chariotit.uva.sc.qdsl.ast.qls.node.widget;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

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
        visitor.visitSpinboxWidget(this);
    }
}
