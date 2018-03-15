package com.chariotit.uva.sc.qdsl.qls.ast.node.property;

import com.chariotit.uva.sc.qdsl.qls.ast.node.Property;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class ColorProperty extends Property {

    private String color;

    public ColorProperty(String color, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.color = color;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
