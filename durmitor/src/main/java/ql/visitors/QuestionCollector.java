package ql.visitors;

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

public class QuestionCollector extends AbstractCollector<Question> implements StatementVisitor {

    @Override
    public List<Question> collect(Form form) {
        
        collection.clear();
        
        visit(form.getBlock());
        
        return collection;
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
        collection.add(stmt);
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        collection.add(stmt);
    }
}
