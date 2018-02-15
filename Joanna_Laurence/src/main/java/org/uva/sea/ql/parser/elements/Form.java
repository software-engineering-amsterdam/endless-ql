package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Traverse;

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

    public void setName(String name) {
        this.name = name;
    }

    public Statements getStatements() {
        return statements;
    }

    public void setStatements(Statements statements) {
        this.statements = statements;
    }

    public void traverse(Traverse traverse, TraverseType traverseType) {
        traverse.doForm(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        if(this.statements != null)
            this.statements.traverse(traverse, traverseType);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
