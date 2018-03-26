package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class FontSize extends StyleSpecification {

    private final int size;

    public FontSize(final Token token, final String size) {
        super(token);
        this.size = Integer.parseInt(size);
    }

    public final int getSize() {
        return this.size;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
