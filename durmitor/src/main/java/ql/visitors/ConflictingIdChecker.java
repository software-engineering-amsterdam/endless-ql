package ql.visitors;

import java.util.ArrayList;
import java.util.List;

import ql.ast.expression.Identifier;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.StatementVisitor;

public class ConflictingIdChecker implements StatementVisitor {

    private Form form;
    private List<String> errors;
    private List<Identifier> identifiers;
    private List<Identifier> duplicates;

    public ConflictingIdChecker(Form form, List<String> errors) {
        
        this.form           = form;
        this.errors         = errors;
        this.identifiers    = new ArrayList<Identifier>();
        this.duplicates     = new ArrayList<Identifier>();
    }
    
    public List<Identifier> getDuplicates() {
        
        visit(form.getBlock());
        
        return duplicates;
    }
    
    public void check(Identifier id) {
        
        for(Identifier orig : identifiers)
        {
            if(orig.getName().equals(id.getName()))
            {
                if(!duplicates.contains(orig))  duplicates.add(orig);
                if(!duplicates.contains(id))    duplicates.add(id);
                
                errors.add("Duplicate identifiers [ "+id.getName()+" ] with different types found @ " + orig.getLocation() + " and " + " @ " + id.getLocation());
            }
        }
        
        identifiers.add(id);
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
        check(stmt.getIdentifier());
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        check(stmt.getIdentifier());
    }
}
