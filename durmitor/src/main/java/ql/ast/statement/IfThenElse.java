package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.visitors.interfaces.ExpressionVisitable;
import ql.visitors.interfaces.ExpressionVisitor;
import ql.visitors.interfaces.StatementVisitor;

public class IfThenElse extends Statement implements ExpressionVisitable {
    
    private Expression condition;
    private Statement thenStmt;
    private Statement elseStmt;
    
    public IfThenElse(Expression condition, Statement thenStmt, Statement elseStmt) {
        this.condition = condition;
        this.thenStmt = thenStmt;
        this.elseStmt = elseStmt;
    }
    
    public Expression getCondition() {
        return condition;
    }

    public Statement getThenStatement() {
        return thenStmt;
    }

    public Statement getElseStatement() {
        return elseStmt;
    }

    @Override
    public String toString() {
        return "if( " +condition.toString() + " ) " + thenStmt.toString() + " else " + elseStmt.toString();
    }

    @Override
    public void accept(StatementVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
