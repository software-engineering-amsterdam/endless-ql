package qls.model;

import org.antlr.v4.runtime.Token;
import qls.IQLSVisitor;

public class Statement extends QLSNode {
    protected Statement(Token start) {
        super(start);
    }

    @Override
    public <T> T accept(IQLSVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
