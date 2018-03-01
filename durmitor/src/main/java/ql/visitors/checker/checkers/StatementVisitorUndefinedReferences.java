package ql.visitors.checker.checkers;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.ExpressionVisitorIdentifier;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorUndefinedReferences implements StatementVisitor {

    private List<String> identifiers;
    private List<String> errors;

    public StatementVisitorUndefinedReferences(List<String> errors) {
        
        this.identifiers    = new ArrayList<String>();
        this.errors         = errors;
    }
    
    private void checkReferences(Expression expr) {
        
        List<Identifier> ids = expr.accept(new ExpressionVisitorIdentifier());
        
        for(Identifier id : ids)
        {
            if (!identifiers.contains(id.getName())) 
            {
                errors.add("Reference to undefined question [" + id.getName() + "] found at " + id.getLocation());
            } 
        }
        
    }

    @Override
    public void visit(Block block) {
        for (Statement stmt : block.getStatements())
            stmt.accept(this);
    }

    
    @Override
    public void visit(IfThen stmt) {
        checkReferences(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    
    @Override
    public void visit(IfThenElse stmt) {
        checkReferences(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    
    @Override
    public void visit(AnswerableQuestion stmt) {
        identifiers.add(stmt.getIdentifier().getName());
    }

    
    @Override
    public void visit(ComputedQuestion stmt) {
        identifiers.add(stmt.getIdentifier().getName());
        checkReferences(stmt.getComputation());
    }
}
