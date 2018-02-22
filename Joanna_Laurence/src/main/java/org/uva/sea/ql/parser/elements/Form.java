package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.BaseVisitor;

public class Form extends ASTNode {

    private String name;
    private Statements statements;

    public Form(String name, Statements statements) {
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
    public void accept(BaseVisitor visitor) {
        visitor.visit(this);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
