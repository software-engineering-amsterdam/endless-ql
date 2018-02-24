package ql.visitors;

import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorInvalidOperands implements StatementVisitor {
    
    private List<String> errors;

    public StatementVisitorInvalidOperands(List<String> errors) {
        this.errors = errors;
    }
    
    public void check(Expression expr) {
        
        Type exprType = expr.accept(new ExpressionVisitorType());
        
        if(exprType.isUndefined())
        {
            errors.add("Illegal operation ["+expr+"] at "+expr.getLocation());
        }
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        check(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        check(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {}

    @Override
    public void visit(ComputedQuestion stmt) {
        check(stmt.getComputation());
    }
}
