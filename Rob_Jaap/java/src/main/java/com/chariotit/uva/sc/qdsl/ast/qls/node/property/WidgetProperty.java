package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.node.WidgetType;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class WidgetProperty extends Property {

    private WidgetType widget;

    public WidgetProperty(WidgetType widget, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.widget = widget;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitWidgetProperty(this);
    }
}
