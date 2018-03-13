package org.uva.sea.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

public class Font extends StyleSpecification {

    private String name;

    public Font(Token token, String name) {
        super(token);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public <T> T accept(IStyleASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
