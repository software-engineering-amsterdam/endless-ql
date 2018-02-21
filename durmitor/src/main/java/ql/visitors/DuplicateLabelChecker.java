package ql.visitors;

import java.util.ArrayList;
import java.util.List;

import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Question;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.StatementVisitor;

public class DuplicateLabelChecker implements StatementVisitor {

    private Form form;
    private List<String> warnings;
    private List<Question> questions;
    private List<String> duplicates;

    public DuplicateLabelChecker(Form form, List<String> warnings) {
        
        this.form       = form;
        this.warnings   = warnings;
        this.questions  = new ArrayList<Question>();
        this.duplicates = new ArrayList<String>();
    }
    
    public List<String> getDuplicates() {
        
        visit(form.getBlock());
        
        return duplicates;
    }
    
    public void check(Question question) {
        
        for(Question orig : questions)
        {
            if(orig.getLabel().equals(question.getLabel()))
            {
                if(!duplicates.contains(orig.getLabel()))       duplicates.add(orig.getLabel());
                if(!duplicates.contains(question.getLabel()))   duplicates.add(question.getLabel());
                
                warnings.add("Duplicate labels [ " + orig.getLabel() + " ] found @ " + orig.getLocation() + " and  @ " + question.getLocation());
            }
        }
        
        questions.add(question);
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
        check(stmt);
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        check(stmt);
    }
}
