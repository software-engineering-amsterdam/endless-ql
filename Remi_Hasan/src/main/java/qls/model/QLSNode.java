package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public abstract class QLSNode {
    private transient Token token;

    protected QLSNode(Token start) {
        this.token = start;
    }

    public Token getToken() {
        return token;
    }

    public String getLocation() {
        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }

    public abstract <T> T accept(IQLSVisitor<T> visitor);
}
