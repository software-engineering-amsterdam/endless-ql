package org.uva.sea.ql.parser.elements.types;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitor;

public class Bool extends ASTNode  {

    private boolean value;

    public Bool(Token token, boolean value) {
        super(token);
        this.value = value;
    }

    public boolean isTrue() {
        return value;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type(NodeType.BOOLEAN);
    }
}
