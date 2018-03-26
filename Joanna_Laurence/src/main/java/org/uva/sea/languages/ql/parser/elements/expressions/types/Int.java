package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Int extends Expression {
    private final int value;

    public Int(final Token token, final String value) {
        super(token);
        this.value = Integer.parseInt(value);
    }

    public Int(final Token token, final int value) {
        super(token);
        this.value = value;
    }

    public final int getValue() {
        return this.value;
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public final Type getType() {
        return new Type(NodeType.INTEGER);
    }
}
