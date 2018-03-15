package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Stylesheet extends QLSNode {

    private final List<Page> pages;
    private final String name;

    public Stylesheet(Token token, String name, List<Page> pages) {
        super(token);
        this.pages = pages;
        this.name = name;
    }

    public Iterable<Page> getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
