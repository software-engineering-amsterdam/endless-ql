package org.uva.sea.languages.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Type extends ASTNode {
    private final NodeType nodeType;

    public Type(Token token, String type) {
        super(token);
        this.nodeType = NodeType.valueOf(type.toUpperCase());
    }

    public Type(NodeType type) {
        this.nodeType = type;
    }

    public NodeType getNodeType() {
        return this.nodeType;
    }

    public Type getType() {
        return new Type(NodeType.STRING);
    } //This does not have a type

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        Type type = (Type) o;

        return (this.nodeType != null) ? (this.nodeType == type.nodeType) : (type.nodeType == null);
    }

    @Override
    public int hashCode() {
        return (this.nodeType != null) ? this.nodeType.hashCode() : 0;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}