package com.chariotit.uva.sc.qdsl.qls.ast.node.property;

import com.chariotit.uva.sc.qdsl.qls.ast.node.Property;
import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

public class FontProperty extends Property {

    private String font;

    public FontProperty(String font, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.font = font;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
