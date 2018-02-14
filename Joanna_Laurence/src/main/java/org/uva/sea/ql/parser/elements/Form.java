package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.traverse.Traverse;

public class Form implements ASTNode {

    private String name;

    public Form(String name, Statements statement) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void traverse(Traverse traverse) {
        traverse.doForm(this);
    }
}
