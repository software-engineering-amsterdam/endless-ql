package ql.visitors;

import java.util.ArrayList;

import ql.ast.expression.Identifier;
import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;
import ql.ast.statement.Statement;
import ql.visitors.interfaces.StatementVisitor;

public class IdCollector implements StatementVisitor {
    
    private ArrayList<Identifier> ids;
    
    public IdCollector() {
        ids = new ArrayList<Identifier>();
    }

    public IdCollector(ArrayList<Identifier> ids) {
        this.ids = ids;
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
        ids.add(stmt.getIdentifier());
    }

    @Override
    public void visit(ComputedQuestion stmt) {
        ids.add(stmt.getIdentifier());
    }
    
}
