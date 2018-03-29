package org.uva.sea.languages.ql.parser.elements.expressions.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.Expression;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Str extends Expression {
    private final String value;

    public Str(Token token, String value) {
        super(token);
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public Type getType() {
        return new Type(NodeType.STRING);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
