package ql.visitors.checker.checkers;

import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.exceptions.NonBooleanCondition;
import ql.exceptions.QLException;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorNonBooleanConditions implements StatementVisitor {
    
    private List<QLException> errors;

    public StatementVisitorNonBooleanConditions(List<QLException> errors) {
        
        this.errors = errors;
    }
    
    private void checkCondition(Expression condition) {
        if(!condition.getType().isBoolean()) errors.add(new NonBooleanCondition(condition));
    }
    
    @Override
    public void visit(Block stmts) {
        for(Statement stmt : stmts.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        checkCondition(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        checkCondition(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
    }

    @Override
    public void visit(ComputedQuestion stmt) {
    }
}
