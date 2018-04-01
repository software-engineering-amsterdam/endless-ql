package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Properties extends AstNode {

    List<Property> properties = new ArrayList<>();

    public Properties(List<Property> properties, SourceFilePosition filePosition) {
        super(filePosition);
        this.properties = properties;
    }

    public Properties(SourceFilePosition filePosition) {
        super(filePosition);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        for (Property property : properties) {
            property.acceptVisitor(visitor);
        }

        visitor.visitProperties(this);
    }
}
