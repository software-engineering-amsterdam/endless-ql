package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Bool extends Expression {

    private final boolean value;

    public Bool(final Token token, final boolean value) {
        super(token);
        this.value = value;
    }

    public final boolean isTrue() {
        return this.value;
    }

    public final Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
