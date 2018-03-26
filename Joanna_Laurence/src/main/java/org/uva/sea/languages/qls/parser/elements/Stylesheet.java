package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

import java.util.List;

public class Stylesheet extends QLSNode {

    private final List<Page> pages;
    private final String name;

    public Stylesheet(final Token token, final String name, final List<Page> pages) {
        super(token);
        this.pages = pages;
        this.name = name;
    }

    public final Iterable<Page> getPages() {
        return this.pages;
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
