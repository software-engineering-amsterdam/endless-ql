package org.uva.sea.languages.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.visitor.IStyleASTVisitor;

public abstract class QLSNode {

    private final Token token;

    protected QLSNode(final Token token) {
        this.token = token;
    }

    public final int getLine() {
        return this.token.getLine();
    }

    public final int getColumn() {
        return this.token.getCharPositionInLine();
    }

    public abstract <T> T accept(IStyleASTVisitor<T> visitor);
}
