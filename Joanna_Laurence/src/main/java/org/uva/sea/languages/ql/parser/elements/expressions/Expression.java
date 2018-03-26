package org.uva.sea.languages.ql.parser.elements.expressions;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.ASTNode;

public abstract class Expression extends ASTNode {
    protected Expression() {
    }

    protected Expression(Token token) {
        super(token);
    }
}
