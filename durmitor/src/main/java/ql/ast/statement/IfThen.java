package ql.ast.statement;

import ql.ast.expression.Expression;
import ql.ast.expression.literal.Literal;
import ql.gui.alternative.ASTtoGUI;
import ql.helpers.Observable;
import ql.helpers.Observer;
import ql.visitors.interfaces.StatementVisitor;

public class IfThen extends Statement implements Observer {
    
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

    @Override
    public void update(Observable observable, Literal<?>[] values) {
        this.accept(new ASTtoGUI());
    }
}
