package com.chariotit.uva.sc.qdsl.ast.qls.node.widget;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class DropdownWidget extends WidgetType {

    private String yesLabel;
    private String noLabel;

    public DropdownWidget(String yesLabel, String noLabel, SourceFilePosition filePosition) {
        super(filePosition);
        this.yesLabel = yesLabel;
        this.noLabel = noLabel;
    }

    public DropdownWidget(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public String getYesLabel() {
        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.BOOLEAN;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitDropdownWidget(this);
    }
}
