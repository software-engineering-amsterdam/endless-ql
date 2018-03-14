package org.uva.sea.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Stylesheet extends QLSNode {

    private List<Page> pages;
    private String name;

    public Stylesheet(Token token, String name, List<Page> pages) {
        super(token);
        this.pages = pages;
        this.name = name;
    }

    public List<Page> getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) throws InterruptedException {
        return visitor.visit(this);
    }
}
