package com.chariotit.uva.sc.qdsl.ast.node;

public class Label extends AstNode {

    private String label;

    public Label(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
