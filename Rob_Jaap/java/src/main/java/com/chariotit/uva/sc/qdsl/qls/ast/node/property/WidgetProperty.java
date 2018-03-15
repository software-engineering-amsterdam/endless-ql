package com.chariotit.uva.sc.qdsl.qls.ast.node.property;

import com.chariotit.uva.sc.qdsl.qls.ast.node.Property;
import com.chariotit.uva.sc.qdsl.qls.ast.node.WidgetType;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class WidgetProperty extends Property {

    private WidgetType widget;

    public WidgetProperty(WidgetType widget, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.widget = widget;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
