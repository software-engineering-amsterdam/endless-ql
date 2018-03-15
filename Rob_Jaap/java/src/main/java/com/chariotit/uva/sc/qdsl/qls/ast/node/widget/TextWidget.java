package com.chariotit.uva.sc.qdsl.qls.ast.node.widget;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.qls.ast.node.WidgetType;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class TextWidget extends WidgetType {

    public TextWidget(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.STRING ||
                expressionType == ExpressionType.INTEGER ||
                expressionType == ExpressionType.MONEY;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
