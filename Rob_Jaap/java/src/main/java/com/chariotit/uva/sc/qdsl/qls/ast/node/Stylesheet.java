package com.chariotit.uva.sc.qdsl.qls.ast.node;

import com.chariotit.uva.sc.qdsl.qls.ast.visitor.NodeVisitor;

import java.util.List;

public class Stylesheet extends AstNode {

    private List<Page> pages;
    private String label;

    public Stylesheet(List<Page> pages, String label, Integer
            lineNumber, Integer
            columnNumber) {
        super(lineNumber, columnNumber);
        this.pages = pages;
        this.label = label;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {

    }
}
