package org.uva.sea.ql.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.NodeType;
import org.uva.sea.ql.elements.types.Type;
import org.uva.sea.visitor.IASTVisitor;

public class Form extends ASTNode {

    private String name;
    private Statements statements;

    public Form(Token token, String name, Statements statements) {
        super(token);
        this.name = name;
        this.statements = statements;
    }

    public String getName() {
        return name;
    }

    public Statements getStatements() {
        return statements;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
