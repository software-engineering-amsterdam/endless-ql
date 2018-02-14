package org.uva.sea.ql.parser.elements;

import java.util.ArrayList;
import java.util.List;

public class Statements {

    private List<Statement> statementList;

    public Statements() {
        statementList = new ArrayList<Statement>();
    }

    public List<Statement> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Statement> statementList) {
        this.statementList = statementList;
    }

    public void addStatment(Statement item) {
        this.statementList.add(item);
    }
}
