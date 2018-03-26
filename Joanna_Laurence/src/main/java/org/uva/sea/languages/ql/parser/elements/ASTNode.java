package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class ASTNode {

    private Token token = null;

    protected ASTNode() {
    }

    protected ASTNode(final Token token) {
        this.token = token;
    }

    public abstract Type getType();

    public final int getLine() {
        return this.token.getLine();
    }

    public final int getColumn() {
        return this.token.getCharPositionInLine();
    }

    public abstract <T> T accept(IASTVisitor<T> visitor);
}
