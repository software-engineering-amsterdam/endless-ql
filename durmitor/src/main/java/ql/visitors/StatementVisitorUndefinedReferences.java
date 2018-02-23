package ql.visitors;

import java.util.List;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.helpers.symboltable.SymbolTable;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorUndefinedReferences implements StatementVisitor {

    private SymbolTable symbolTable;
    private List<String> errors;

    public StatementVisitorUndefinedReferences(SymbolTable symbolTable, List<String> errors) {
        
        this.symbolTable    = symbolTable;
        this.errors         = errors;
    }
    
    private void checkReferences(List<Identifier> ids) {
        
        for(Identifier id : ids)
        {
            int firstIndex = symbolTable.getFirstIndexOf(id.getName());
            
            if (firstIndex == -1) 
            {
                errors.add("Reference to undefined question [" + id.getName() + "] found at " + id.getLocation());
            } 
            else 
            {
                Identifier firstDefinition = symbolTable.get(firstIndex).getIdentifier();
                
                if (id.getLocation().precedes(firstDefinition.getLocation())) 
                {
                    errors.add("Question [" + id.getName() + "] used before definition at " + id.getLocation());
                }
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
        checkReferences(stmt.getCondition().accept(new ExpressionVisitorIdentifier()));
        stmt.getThenStatement().accept(this);
    }

    
    @Override
    public void visit(IfThenElse stmt) {
        checkReferences(stmt.getCondition().accept(new ExpressionVisitorIdentifier()));
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    
    @Override
    public void visit(AnswerableQuestion stmt) {}

    
    @Override
    public void visit(ComputedQuestion stmt) {
        checkReferences(stmt.getComputation().accept(new ExpressionVisitorIdentifier()));
    }
}
