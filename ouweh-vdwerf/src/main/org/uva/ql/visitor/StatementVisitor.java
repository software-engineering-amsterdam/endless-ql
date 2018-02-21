package main.org.uva.ql.visitor;

import main.org.uva.ql.ast.*;


public interface StatementVisitor<T, C> {

    T visit(Question question, C context);

    T visit(Conditional conditional, C context);
}