package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class FontSizeProperty extends Property {

    private Integer size;

    public FontSizeProperty(Integer size, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.size = size;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitFontSizeProperty(this);
    }
}
