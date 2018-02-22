package ql.visitors;

import ql.ast.statements.Question;

public interface StatementVisitor<T> {
    public T visit(Question question);
}
