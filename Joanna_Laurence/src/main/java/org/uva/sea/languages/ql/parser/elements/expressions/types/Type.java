package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Type extends Expression {
    private final NodeType nodeType;

    public Type(final Token token, final String type) {
        super(token);
        this.nodeType = NodeType.valueOf(type.toUpperCase());
    }

    public Type(final NodeType type) {
        this.nodeType = type;
    }

    public final NodeType getNodeType() {
        return this.nodeType;
    }

    public final Type getType() {
        return new Type(NodeType.STRING);
    } //This does not have a type

    @Override
    public final boolean equals(final Object o) {
        if (this == o) return true;
        if ((o == null) || (this.getClass() != o.getClass())) return false;

        final Type type = (Type) o;

        return (this.nodeType != null) ? (this.nodeType == type.nodeType) : (type.nodeType == null);
    }

    @Override
    public final int hashCode() {
        return (this.nodeType != null) ? this.nodeType.hashCode() : 0;
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}