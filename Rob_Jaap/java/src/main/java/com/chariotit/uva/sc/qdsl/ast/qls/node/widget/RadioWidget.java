package com.chariotit.uva.sc.qdsl.ast.qls.node.widget;

import com.chariotit.uva.sc.qdsl.ast.ql.type.ExpressionType;
import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class RadioWidget extends WidgetType {

    private String yesLabel;
    private String noLabel;

    public RadioWidget(String yesLabel, String noLabel, SourceFilePosition filePosition) {
        this(filePosition);
        this.yesLabel = yesLabel;
        this.noLabel = noLabel;
    }

    public RadioWidget(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public String getNoLabel() {
        return noLabel;
    }

    public void setNoLabel(String noLabel) {
        this.noLabel = noLabel;
    }

    public String getYesLabel() {

        return yesLabel;
    }

    public void setYesLabel(String yesLabel) {
        this.yesLabel = yesLabel;
    }

    @Override
    public boolean isValidExpressionType(ExpressionType expressionType) {
        return expressionType == ExpressionType.BOOLEAN;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitRadioWidget(this);
    }
}
