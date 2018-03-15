package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class IfStatement extends ASTNode {

    private ASTNode expression;

    private Statements then;
    private Statements otherwise;

    public IfStatement(Token token, ASTNode expression, Statements then, Statements otherwise) {
        super(token);
        this.then = then;
        this.expression = expression;
        this.otherwise = otherwise;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public Statements getThen() {
        return then;
    }

    public Statements getOtherwise() {
        return otherwise;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
