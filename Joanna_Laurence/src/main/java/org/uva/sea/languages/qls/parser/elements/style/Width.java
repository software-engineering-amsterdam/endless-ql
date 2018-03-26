package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Width extends StyleSpecification {

    private final int width;

    public Width(final Token token, final String width) {
        super(token);
        this.width = Integer.parseInt(width);
    }

    public final int getWidth() {
        return this.width;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
