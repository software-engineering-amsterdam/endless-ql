package org.uva.sea.qls.parser.elements.specification;

import org.antlr.v4.runtime.Token;
import org.uva.sea.qls.parser.elements.QLSNode;

public abstract class Specification extends QLSNode {
    public Specification(Token token) {
        super(token);
    }
}
