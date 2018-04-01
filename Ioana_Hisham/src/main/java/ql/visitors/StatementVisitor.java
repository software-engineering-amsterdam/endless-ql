package ql.visitors;

import ql.ast.statements.CalculableQuestion;
import ql.ast.statements.IfThen;
import ql.ast.statements.IfThenElse;
import ql.ast.statements.Question;

public interface StatementVisitor<T> {
    T visit(CalculableQuestion calculableQuestion);
    T visit(IfThen ifThen);
    T visit(IfThenElse ifThenElse);
    T visit(Question question);
}
