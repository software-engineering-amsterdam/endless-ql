package QL.Visitors;

import QL.AST.Question;
public interface QuestionVisitorInterface<T> {
    T visit(Question question);
}
