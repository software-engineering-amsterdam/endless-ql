package ql.visitors;

import java.util.HashMap;
import java.util.Map;

import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.ast.type.Type;
import ql.visitors.interfaces.StatementVisitor;

public class SymbolTable implements StatementVisitor {
    
    private Map<String,Type> symbolTable;
    
    public SymbolTable() {
        symbolTable = new HashMap<String,Type>();
    }
    
    public Map<String,Type> build(Form form) {
        
        symbolTable.clear();
        
        visit(form.getBlock());
        
        return symbolTable;
    }
    
    @Override
    public void visit(Block stmts) {
        for(Statement stmt : stmts.getStatements()) {
           stmt.accept(this);
        }
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
        symbolTable.put(stmt.getIdentifier().getName(),stmt.getType());
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        symbolTable.put(stmt.getIdentifier().getName(),stmt.getType());
    }
    
}
