package com.chariotit.uva.sc.qdsl.ast.node;

import com.chariotit.uva.sc.qdsl.ast.visitor.NodeVisitor;

public class Label extends AstNode {

    private String label;

    public Label(String label, Integer lineNumber, Integer columnNumber) {
        super(lineNumber, columnNumber);
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        visitor.visitLabel(this);
    }
}
