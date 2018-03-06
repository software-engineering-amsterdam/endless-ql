package ql.visitors;

import java.util.HashSet;
import java.util.Set;

import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.helpers.Observable;
import ql.visitors.checker.checkers.ExpressionVisitorIdentifier;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorObservables implements StatementVisitor {

    private Set<Observable> observables;
    
    public StatementVisitorObservables() {
        observables = new HashSet<Observable>();
    }
    
    public StatementVisitorObservables(Set<Observable> observables) {
        this.observables = observables;
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        
        observables.addAll(stmt.getCondition().accept(new ExpressionVisitorIdentifier()));
        
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        
        observables.addAll(stmt.getCondition().accept(new ExpressionVisitorIdentifier()));
        
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        observables.addAll(stmt.getComputation().accept(new ExpressionVisitorIdentifier()));
    }
}
