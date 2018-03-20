package org.uva.sea.languages.ql.parser.elements;

import org.uva.sea.languages.ql.parser.NodeType;
import org.uva.sea.languages.ql.parser.elements.types.Type;
import org.uva.sea.languages.ql.parser.visitor.IASTVisitor;

import java.util.ArrayList;
import java.util.List;

public class Statements extends ASTNode {

    private final List<ASTNode> statementList;

    public Statements() {
        this.statementList = new ArrayList<>();
    }

    public Iterable<ASTNode> getStatementList() {
        return this.statementList;
    }

    public void addStatement(ASTNode item) {
        this.statementList.add(item);
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(IASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}
