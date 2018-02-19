package ql.visitors.interfaces;

import ql.ast.statement.AnswerableQuestion;
import ql.ast.statement.Block;
import ql.ast.statement.ComputedQuestion;
import ql.ast.statement.IfThen;
import ql.ast.statement.IfThenElse;

public interface StatementVisitor {

    void visit(Block stmts);
    void visit(IfThen stmt);
    void visit(IfThenElse stmt);
    void visit(AnswerableQuestion stmt);
    void visit(ComputedQuestion stmt);
}
