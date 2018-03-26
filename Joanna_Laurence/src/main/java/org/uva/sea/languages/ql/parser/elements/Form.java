package org.uva.sea.languages.ql.parser.elements;

import org.antlr.v4.runtime.Token;
import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

public class Form extends ASTNode {

    private final String name;
    private final Statements statements;

    public Form(final Token token, final String name, final Statements statements) {
        super(token);
        this.name = name;
        this.statements = statements;
    }

    public final String getName() {
        return this.name;
    }

    public final Statements getStatements() {
        return this.statements;
    }

    public final Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
