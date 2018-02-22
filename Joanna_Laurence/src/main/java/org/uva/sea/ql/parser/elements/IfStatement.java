package org.uva.sea.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.traverse.Visitor;

public class IfStatement extends ASTNode {

    private ASTNode expression;

    private Statements statements;

    public IfStatement(Token token, ASTNode expression, Statements statements)
    {
        super(token);
        this.statements = statements;
        this.expression = expression;
    }

    public ASTNode getExpression() {
        return expression;
    }

    public Statements getStatements() {
        return statements;
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
