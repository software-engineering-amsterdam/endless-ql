package QL.Analysis;

import QL.AST.Question;
public interface QuestionVisitorInterface<T> {
    T visit(Question question);
}
