package ql.model;

import org.antlr.v4.runtime.Token;

public abstract class Statement extends QLNode {

    public Statement(Token start) {
        super(start);
    }
}
