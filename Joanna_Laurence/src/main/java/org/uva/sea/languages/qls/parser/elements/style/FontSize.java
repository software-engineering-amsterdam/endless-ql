package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public class FontSize extends StyleSpecification {

    private int size;

    public FontSize(Token token, String size) {
        super(token);
        this.size = Integer.parseInt(size);
    }

    public int getSize() {
        return size;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
