package com.chariotit.uva.sc.qdsl.qls.ast.node;

import com.chariotit.uva.sc.qdsl.ql.ast.node.AstNode;
import com.chariotit.uva.sc.qdsl.ql.ast.visitor.NodeVisitor;

import java.util.ArrayList;
import java.util.List;

public class Properties extends AstNode {

    List<Property> properties = new ArrayList<>();

    public Properties(List<Property> properties, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.properties = properties;
    }

    public Properties(Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
