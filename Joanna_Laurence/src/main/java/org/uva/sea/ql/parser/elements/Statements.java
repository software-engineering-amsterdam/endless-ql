package org.uva.sea.ql.parser.elements;

import java.util.ArrayList;
import java.util.List;

public class Statements extends Expr {

    private List<Expr> statementList;

    public Statements() {
        statementList = new ArrayList<Expr>();
    }

    public List<Expr> getStatementList() {
        return statementList;
    }

    public void setStatementList(List<Expr> statementList) {
        this.statementList = statementList;
    }

    public void addStatement(Expr item) {
        this.statementList.add(item);
    }
}
