package org.uva.sea.languages.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.elements.types.Type;
import org.uva.sea.languages.ql.parser.nodeTypes.SingleNode;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Positive extends SingleNode {
    public Positive(Token token, ASTNode value) {
        super(token, value);
    }

    public Type getType() {
        return this.getValue().getType();
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
