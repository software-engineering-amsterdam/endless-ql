package ql.visitors;

import java.util.List;

import ql.ast.expression.Expression;
import ql.ast.form.Form;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.StatementVisitor;

public class ConditionCollector extends AbstractCollector<Expression> implements StatementVisitor {
    
    @Override
    public List<Expression> collect(Form form) {
        
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
        collection.add(stmt.getCondition());
        stmt.getThenStatement().accept(this);
    }

    @Override
    public void visit(IfThenElse stmt) {
        collection.add(stmt.getCondition());
        stmt.getThenStatement().accept(this);
        stmt.getElseStatement().accept(this);
    }

    @Override
    public void visit(AnswerableQuestion stmt) {
    }

    @Override
    public void visit(ComputedQuestion stmt) {
    }
}
