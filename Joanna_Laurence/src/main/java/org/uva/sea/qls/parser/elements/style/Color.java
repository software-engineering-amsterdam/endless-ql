package org.uva.sea.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

public class Color extends StyleSpecification {

    private int colorCode;

    public Color(Token token, String colorCode) {
        super(token);
        this.colorCode = Integer.parseInt(colorCode);
    }

    public int getColorCode() {
        return colorCode;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
