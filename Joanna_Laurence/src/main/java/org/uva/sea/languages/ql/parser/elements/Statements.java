package org.uva.sea.languages.ql.parser.elements;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.expressions.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class Statements extends ASTNode {

    private final List<Statement> statementList;

    public Statements() {
        this.statementList = new ArrayList<>();
    }

    public final Iterable<Statement> getStatementList() {
        return this.statementList;
    }

    public final void addStatement(final Statement item) {
        this.statementList.add(item);
    }

    public final Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public final <T> T accept(final IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
