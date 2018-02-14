package ql.ast.statement;

import ql.ast.expression.Expression;

public class IfThenElse extends Statement {
    
    private Expression condition;
    private Statement thenStmt;
    private Statement elseStmt;
    
    public IfThenElse(Expression condition, Statement thenStmt, Statement elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
}
