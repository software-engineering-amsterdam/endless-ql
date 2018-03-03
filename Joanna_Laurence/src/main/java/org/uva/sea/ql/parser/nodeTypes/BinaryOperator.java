package org.uva.sea.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.visitor.IASTVisitor;

public abstract class BinaryOperator extends ASTNode {
    private ASTNode lhs;
    private ASTNode rhs;

    public BinaryOperator(Token token, ASTNode lhs, ASTNode rhs) {
        super(token);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public ASTNode getLhs() {
        return lhs;
    }

    public ASTNode getRhs() {
        return rhs;
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
