package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitor;

//TODO: Override equals
public class Type extends ASTNode  {
    private NodeType nodeType;

    public Type(String type) {
        this.nodeType = NodeType.valueOf(type.toUpperCase());
    }

    public Type(NodeType type) {
        this.nodeType = type;
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("string");
    } //This does not have a type

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Type type = (Type) o;

        return nodeType != null ? nodeType.equals(type.nodeType) : type.nodeType == null;
    }

    @Override
    public int hashCode() {
        return nodeType != null ? nodeType.hashCode() : 0;
    }
}