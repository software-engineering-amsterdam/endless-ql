package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class FontSizeProperty extends Property {

    private Integer size;

    public FontSizeProperty(Integer size, SourceFilePosition filePosition) {
        super(filePosition);
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitFontSizeProperty(this);
    }
}
