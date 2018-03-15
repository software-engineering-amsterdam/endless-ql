package com.chariotit.uva.sc.qdsl.ast.qls.node.widget;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class SliderWidget extends WidgetType {

    public SliderWidget(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.MONEY ||
                expressionType == ExpressionType.INTEGER;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitSliderWidget(this);
    }
}
