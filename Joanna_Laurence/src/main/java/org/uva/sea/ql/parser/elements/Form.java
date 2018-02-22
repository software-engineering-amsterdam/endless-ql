package org.uva.sea.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Visitor;

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

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }
}
