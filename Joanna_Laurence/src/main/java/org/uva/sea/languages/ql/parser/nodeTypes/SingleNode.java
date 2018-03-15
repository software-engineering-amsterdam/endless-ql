package org.uva.sea.languages.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.ASTNode;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class SingleNode extends ASTNode {
    private ASTNode value;

    public SingleNode(Token token, ASTNode value) {
        super(token);
        this.value = value;
    }

    public ASTNode getValue() {
        return value;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
