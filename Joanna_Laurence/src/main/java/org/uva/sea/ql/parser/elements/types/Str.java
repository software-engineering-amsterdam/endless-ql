package org.uva.sea.ql.parser.elements.types;

import org.uva.sea.ql.parser.NodeType;
import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.visitor.Visitor;

public class Str extends ASTNode  {
    private String value;

    public Str(Token token, String value) {
        super(token);
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Type getType() {
        return new Type(NodeType.STRING);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
