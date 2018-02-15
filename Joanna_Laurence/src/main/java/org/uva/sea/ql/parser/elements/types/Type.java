package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Traverse;

//TODO: Override equals
public class Type implements ASTNode {
    private String nodeType;

    public Type(String type) {
        this.nodeType = type;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeType(String type) {
        this.nodeType = type;
    }

    public void traverse(Traverse traverse) {
        traverse.doType(this);
    }

    public Type getType() {
        return new Type("string");
    }
}
