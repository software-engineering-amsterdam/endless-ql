package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Width extends StyleSpecification {

    private int width;

    public Width(Token token, String width) {
        super(token);
        this.width = Integer.parseInt(width);
    }

    public int getWidth() {
        return width;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
