package ql.model.statement;

import org.antlr.v4.runtime.Token;
import ql.model.QLNode;

public abstract class Statement extends QLNode {

    public Statement(Token start) {
        super(start);
    }
}
