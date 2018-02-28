package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.visitors.interfaces.StatementVisitor;

public class IfThen extends Statement {
    
    private Expression condition;
    private Statement thenStmt;
    
    public IfThen(Expression condition, Statement thenStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
    }
    
    public Expression getCondition() {
        return condition;
    }
    
    public Statement getThenStatement() {
        return thenStmt;
    }

    @Override
    public String toString() {
        return "if( " +condition.toString() + " ) " + thenStmt.toString();
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
}
