package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public abstract class QLSNode {

    private final Token token;

    public QLSNode(Token token) {
        this.token = token;
    }

    public int getLine() {
        return this.token.getLine();
    }

    public int getColumn() {
        return this.token.getCharPositionInLine();
    }

    public abstract <T> T accept(IStyleASTVisitor<T> visitor);
}
