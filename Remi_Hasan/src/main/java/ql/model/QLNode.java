package ql.model;

import org.antlr.v4.runtime.Token;

public class QLNode {
    private Token token;

    public QLNode(Token start) {
        this.token = start;
    }

    public Token getToken() {
        return token;
    }

    public String getLocation() {
        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }
}
