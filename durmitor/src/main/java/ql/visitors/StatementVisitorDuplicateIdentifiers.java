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

public class StatementVisitorDuplicateIdentifiers implements StatementVisitor {

    private SymbolTable symbolTable;
    private List<String> errors;
    
    public StatementVisitorDuplicateIdentifiers(SymbolTable symbolTable, List<String> errors) {
        
        this.symbolTable    = symbolTable;
        this.errors         = errors;
    }
    
    private void check(Identifier id)
    {
        int occurences = symbolTable.getNrOccurences(id.getName());
        
        if(occurences > 1)
        {
            errors.add("Duplicated identifier ["+id.getName()+"] found at "+id.getLocation());
        }
    }

    @Override
    public void visit(Block block) {
        for(Statement stmt : block.getStatements()) stmt.accept(this);
    }

    @Override
    public void visit(IfThen stmt) {
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
        check(stmt.getIdentifier());
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        check(stmt.getIdentifier());
    }
}
