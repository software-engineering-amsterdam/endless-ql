package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Font extends StyleSpecification {

    private final String name;

    public Font(final Token token, final String name) {
        super(token);
        this.name = name;
    }

    public final String getName() {
        return this.name;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
