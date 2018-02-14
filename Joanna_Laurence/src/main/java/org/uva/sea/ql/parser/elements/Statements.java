package org.uva.sea.ql.parser.elements;

import java.util.ArrayList;
import java.util.List;

public class Statements extends ASTNode {

    private List<ASTNode> statementList;

    public Statements() {
        statementList = new ArrayList<ASTNode>();
    }

    public List<ASTNode> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<ASTNode> statementList) {
        this.statementList = statementList;
    }

    public void addStatement(ASTNode item) {
        this.statementList.add(item);
    }
}
