package com.chariotit.uva.sc.qdsl.ast.qls.node;

import com.chariotit.uva.sc.qdsl.ast.common.SourceFilePosition;
import com.chariotit.uva.sc.qdsl.ast.qls.visitor.NodeVisitor;

import java.util.List;

public class Stylesheet extends AstNode {

    private List<Page> pages;
    private String label;

    public Stylesheet(List<Page> pages, String label, SourceFilePosition filePosition) {
        super(filePosition);
        this.pages = pages;
        this.label = label;
    }

    public List<Page> getPages() {
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public void acceptVisitor(NodeVisitor visitor) {
        for (Page page : pages) {
            page.acceptVisitor(visitor);
        }

        visitor.visitStylesheet(this);
    }
}
