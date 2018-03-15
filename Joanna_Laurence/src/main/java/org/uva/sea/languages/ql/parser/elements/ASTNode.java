package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.elements.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public abstract class ASTNode {

    private Token token;

    public ASTNode() {
    }

    public ASTNode(Token token) {
        this.token = token;
    }

    /**
     * Get the node type
     *
     * @return The type
     */
    public abstract Type getType();

    public int getLine() {
        return token.getLine();
    }

    public int getColumn() {
        return token.getCharPositionInLine();
    }

    public abstract <T> T accept(IASTVisitor<T> visitor);
}
