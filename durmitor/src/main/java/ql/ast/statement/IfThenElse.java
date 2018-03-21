package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.literal.Literal;
import ql.gui.alternative.ASTtoGUI;
import ql.helpers.Observable;
import ql.helpers.Observer;
import ql.visitors.interfaces.StatementVisitor;

public class IfThenElse extends Statement implements Observer {
    
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
    public void update(Observable observable, Literal<?>[] values) {
        this.accept(new ASTtoGUI());
    }
}
