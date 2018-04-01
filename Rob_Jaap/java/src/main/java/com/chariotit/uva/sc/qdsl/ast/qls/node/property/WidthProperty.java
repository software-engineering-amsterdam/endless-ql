package com.chariotit.uva.sc.qdsl.ast.qls.node.property;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.node.Property;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

public class WidthProperty extends Property {

    private Integer width;


    public WidthProperty(Integer width, SourceFilePosition filePosition) {
        super(filePosition);
        this.width = width;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitWidthProperty(this);
    }
}
