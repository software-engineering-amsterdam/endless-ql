package com.chariotit.uva.sc.qdsl.qls.ast.node.widget;

import com.chariotit.uva.sc.qdsl.ql.ast.ExpressionType;
import com.chariotit.uva.sc.qdsl.qls.ast.node.WidgetType;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class DropdownWidget extends WidgetType {

    private String yesLabel;
    private String noLabel;

    public DropdownWidget(String yesLabel, String noLabel, Integer lineNumber, Integer
            columnNumber) {
        super(lineNumber, columnNumber);
        this.yesLabel = yesLabel;
        this.noLabel = noLabel;
    }

    public DropdownWidget(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.BOOLEAN;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
