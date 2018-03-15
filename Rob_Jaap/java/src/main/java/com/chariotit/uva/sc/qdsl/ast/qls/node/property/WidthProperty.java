package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class WidthProperty extends Property {

    private Integer width;


    public WidthProperty(Integer width, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.width = width;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitWidthProperty(this);
    }
}
