package org.uva.sea.ql.parser.elements;


public class Form extends ASTNode {

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
}
