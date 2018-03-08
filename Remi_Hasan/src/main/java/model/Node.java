package model;

import org.antlr.v4.runtime.Token;

public class Node {
    private Token token;

    public Node(Token start) {
        this.token = start;
    }

    public Token getToken() {
        return token;
    }

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }
}
