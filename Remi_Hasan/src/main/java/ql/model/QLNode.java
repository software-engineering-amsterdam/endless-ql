package ql.model;

import org.antlr.v4.runtime.Token;
import ql.IQLVisitor;

public abstract class QLNode {
    private transient Token token;

    public QLNode(Token start) {
        this.token = start;
    }

    public Token getToken() {
        return token;
    }

    public String getLocation() {
        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }

    public abstract <T> T accept(IQLVisitor<T> visitor);
}
