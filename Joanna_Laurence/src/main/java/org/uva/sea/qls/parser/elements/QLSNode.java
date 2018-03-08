package org.uva.sea.qls.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.visitor.IStyleASTVisitor;

public abstract class QLSNode {

    private Token token;

    public QLSNode(Token token) {
        this.token = token;
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public abstract <T> T accept(IStyleASTVisitor<T> visitor);
}
