package org.uva.sea.ql.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.elements.ASTNode;
import org.uva.sea.ql.nodeTypes.SingleNode;
import org.uva.sea.ql.NodeType;
import org.uva.sea.ql.elements.types.Type;
import org.uva.sea.visitor.IASTVisitor;

public class Negative extends SingleNode {
    public Negative(Token token, ASTNode value) {
        super(token, value);
    }

    public Type getType() {
        return new Type(NodeType.BOOLEAN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

