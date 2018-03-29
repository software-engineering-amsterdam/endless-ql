package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public abstract class QLSNode {
    private transient Token token;

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public String getLocation() {
        if (this.token == null) {
            return "";
        }
        return "(" + token.getLine() + ":" + token.getCharPositionInLine() + ")";
    }

    public abstract <T> T accept(IQLSVisitor<T> visitor);
}
