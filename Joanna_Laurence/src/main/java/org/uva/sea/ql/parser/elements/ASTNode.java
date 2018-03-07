package org.uva.sea.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.interpreter.visitor.IASTVisitor;

public abstract class ASTNode {

    private int line;
    private int column;
    private Token token;

    public ASTNode() {
    }

    public ASTNode(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    /**
     * Get the node type
     *
     * @return The type
     */
    public abstract Type getType();

    public int getLine() {
        return this.line;
    }

    public int getColumn() {
        return this.column;
    }

    public Token getToken() {
        return token;
    }

    public abstract <T> T accept(IASTVisitor<T> visitor);
}
