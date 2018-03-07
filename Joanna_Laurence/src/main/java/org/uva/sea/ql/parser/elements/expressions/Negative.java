package org.uva.sea.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.interpreter.visitor.IASTVisitor;

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

