package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class Color extends StyleSpecification {

    private final String colorCode;

    public Color(final Token token, final String colorCode) {
        super(token);
        this.colorCode = colorCode;
    }

    public final String getColorCode() {
        return this.colorCode;
    }

    @Override
    public final <T> T accept(final IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
