package com.chariotit.uva.sc.qdsl.ast.qls.node.widget;

import com.chariotit.uva.sc.qdsl.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class TextWidget extends WidgetType {

    public TextWidget(SourceFilePosition filePosition) {
        super(filePosition);
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.STRING ||
                expressionType == ExpressionType.INTEGER ||
                expressionType == ExpressionType.MONEY;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitTextWidget(this);
    }
}
