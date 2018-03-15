package org.uva.sea.languages.qls.parser.elements.style;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.qls.parser.elements.QLSNode;

public abstract class StyleSpecification extends QLSNode {
    public StyleSpecification(Token token) {
        super(token);
    }
}