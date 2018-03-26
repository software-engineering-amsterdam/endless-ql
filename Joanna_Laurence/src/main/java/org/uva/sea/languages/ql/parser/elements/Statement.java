package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;

public abstract class Statement extends ASTNode {
    Statement(final Token token) {
        super(token);
    }
}
