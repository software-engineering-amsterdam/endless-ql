package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Str extends Expression {
    private final String value;

    public Str(final Token token, final String value) {
        super(token);
        this.value = value;
    }

    public final String getValue() {
        return this.value;
    }

    public final Type getType() {
        return new Type(NodeType.STRING);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
