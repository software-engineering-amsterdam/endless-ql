package org.uva.sea.ql.parser.nodeTypes;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.ASTNode;
import org.uva.sea.ql.traverse.Visitable;
import org.uva.sea.ql.traverse.Visitor;

public abstract class BinaryOperator extends ASTNode implements Visitable {
    private ASTNode lhs;
    private ASTNode rhs;

    public BinaryOperator(Token token, ASTNode lhs, ASTNode rhs) {
        super(token);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public BinaryOperator(ASTNode lhs, ASTNode rhs) {
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
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
