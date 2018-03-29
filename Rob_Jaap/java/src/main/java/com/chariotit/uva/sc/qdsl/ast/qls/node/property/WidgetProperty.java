package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class WidgetProperty extends Property {

    private WidgetType widget;

    public WidgetProperty(WidgetType widget, SourceFilePosition filePosition) {
        super(filePosition);
        this.widget = widget;
    }

    public WidgetType getWidget() {
        return widget;
    }

    public void setWidget(WidgetType widget) {
        this.widget = widget;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitWidgetProperty(this);
    }
}
