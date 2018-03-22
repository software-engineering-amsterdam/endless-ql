package ql.visitors;

import ql.ast.statements.Question;

public interface StatementVisitor<T> {
    T visit(Question question);
}
