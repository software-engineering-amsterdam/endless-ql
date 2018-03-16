package qls.model;

import org.antlr.v4.runtime.Token;

public class QLSNode {
    private Token token;

    public QLSNode(Token start) {
        this.token = start;
    }

    public Token getToken() {
        return token;
    }

    public String getLocation() {
        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }
}
