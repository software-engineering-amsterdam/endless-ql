package org.uva.sea.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitor;

public class Int extends ASTNode  {
    private int value;

    public Int(Token token, String value) {
        super(token);
        this.value = Integer.parseInt(value);
    }

    public Int(Token token, int value) {
        super(token);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type(NodeType.INTEGER);
    }
}
