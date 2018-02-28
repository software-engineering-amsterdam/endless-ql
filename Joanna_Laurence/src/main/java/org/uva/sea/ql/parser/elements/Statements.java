package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.parser.NodeType;
import org.uva.sea.ql.parser.elements.types.Type;
import org.uva.sea.ql.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class Statements extends ASTNode {

    private List<ASTNode> statementList;

    public Statements() {
        statementList = new ArrayList<>();
    }

    public List<ASTNode> getStatementList() {
        return statementList;
    }

    public void addStatement(ASTNode item) {
        this.statementList.add(item);
    }

    public Type getType() {
        return new Type(NodeType.UNKNOWN);
    }

    @Override
    public <T> T accept(Visitor<T> visitor) {
        return visitor.visit(this);
    }
}
