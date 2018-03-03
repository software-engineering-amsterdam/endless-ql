package ql.visitors.checker.checkers;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.helpers.Dependencies;
import ql.helpers.Dependency;
import ql.visitors.ExpressionVisitorIdentifier;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorDependencies implements StatementVisitor {

    private List<Identifier> dependents;
    private List<Identifier> identifiers;
    private Dependencies dependencies;
    
    public StatementVisitorDependencies(Dependencies dependencies) {
        this.dependents     = new ArrayList<Identifier>();
        this.identifiers    = new ArrayList<Identifier>();;
        this.dependencies   = dependencies;
    }
    
    public StatementVisitorDependencies(List<Identifier> dependents, List<Identifier> identifiers, Dependencies dependencies) {
        this.dependents     = dependents;
        this.identifiers    = identifiers;
        this.dependencies   = dependencies;
    }
    
    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        
        List<Identifier> stmtIdentifiers    = stmt.getCondition().accept(new ExpressionVisitorIdentifier());
        List<Identifier> childIdentifiers   = new ArrayList<Identifier>(stmtIdentifiers);
        List<Identifier> childDependents    = new ArrayList<Identifier>();
        
        identifiers.addAll(stmtIdentifiers);
        
        stmt.getThenStatement().accept(new StatementVisitorDependencies(childIdentifiers,childDependents,dependencies));
        
        for(Identifier dependent : childDependents)
        {
            for(Identifier dependsOn : identifiers)
            {
                dependencies.add(new Dependency(dependent, dependsOn));
            }
        }
        
        dependents.clear();
    }

    @Override
    public void visit(IfThenElse stmt) {
        
        List<Identifier> stmtIdentifiers    = stmt.getCondition().accept(new ExpressionVisitorIdentifier());
        List<Identifier> childIdentifiers   = new ArrayList<Identifier>(stmtIdentifiers);
        List<Identifier> childDependents    = new ArrayList<Identifier>();
        
        identifiers.addAll(stmtIdentifiers);
        
        stmt.getThenStatement().accept(new StatementVisitorDependencies(childIdentifiers,childDependents,dependencies));
        stmt.getElseStatement().accept(new StatementVisitorDependencies(childIdentifiers,childDependents,dependencies));
        
        for(Identifier dependent : childDependents)
        {
            for(Identifier dependsOn : identifiers)
            {
                dependencies.add(new Dependency(dependent, dependsOn));
            }
        }
        
        dependents.clear();
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
        dependents.add(stmt.getIdentifier());
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        
        dependents.add(stmt.getIdentifier());
        
        List<Identifier> uses = stmt.getComputation().accept(new ExpressionVisitorIdentifier());
        
        for(Identifier use : uses)
        {
            dependencies.add(new Dependency(stmt.getIdentifier(), use));
        }
    }
    
}
