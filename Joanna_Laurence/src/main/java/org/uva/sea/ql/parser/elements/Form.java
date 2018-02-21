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

    public Statements getStatements() {
        return statements;
    }

    public void traverseNode(Traverse traverse, TraverseType traverseType) {
        traverse.doForm(this);
    }

    public void traverseChildren(Traverse traverse, TraverseType traverseType) {
        if(this.statements != null)
            this.statements.doTraversal(traverse, traverseType);
    }

    public Type getType() {
        return new Type("undefined");
    }
}
