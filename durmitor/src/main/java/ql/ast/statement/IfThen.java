package ql.ast.statement;

import ql.ast.expression.Expression;

public class IfThen extends Statement {
    
    private Expression condition;
    private Statement thenStmt;
    
    public IfThen(Expression condition, Statement thenStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
    }

    @Override
    public String toString() {
        return "if( " +condition.toString() + " ) " + thenStmt.toString();
    }
}
