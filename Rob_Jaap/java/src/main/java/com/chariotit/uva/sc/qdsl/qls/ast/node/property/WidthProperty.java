package com.chariotit.uva.sc.qdsl.qls.ast.node.property;

import com.chariotit.uva.sc.qdsl.qls.ast.node.Property;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class WidthProperty extends Property {

    private Integer width;


    public WidthProperty(Integer width, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.width = width;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
