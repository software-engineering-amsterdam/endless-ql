package qls.model;

import org.antlr.v4.runtime.Token;

public abstract class Statement extends QLSNode {

    protected Statement(Token start) {
        super(start);
    }
}
