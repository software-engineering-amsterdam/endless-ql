package qls.model.style;

import org.antlr.v4.runtime.Token;
import qls.model.QLSNode;

public abstract class StyleAttribute extends QLSNode {

    StyleAttribute(Token start) {
        super(start);
    }

}
