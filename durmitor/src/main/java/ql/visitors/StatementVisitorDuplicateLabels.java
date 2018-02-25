package ql.visitors;

import java.util.ArrayList;
import java.util.List;

import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Question;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.StatementVisitor;

public class StatementVisitorDuplicateLabels implements StatementVisitor {
    
    private List<String> warnings;
    private List<String> labels;
    
    
    public StatementVisitorDuplicateLabels(List<String> warnings) {
        
        this.warnings   = warnings;
        this.labels     = new ArrayList<String>();
    }
    
    private void checkLabel(Question question) {
        
        if(!labels.contains(question.getLabel()))
        {
            labels.add(question.getLabel());
        }
        else
        {
            warnings.add("Duplicate label [\""+question.getLabel()+"\"] found at "+question.getLocation());
        }
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
        checkLabel(stmt);
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        checkLabel(stmt);
    }
    
}
