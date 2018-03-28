package qls.model.statement;

import org.antlr.v4.runtime.Token;
import qls.model.QLSNode;

public abstract class Statement extends QLSNode {

    protected Statement(Token start) {
        super(start);
    }
}
