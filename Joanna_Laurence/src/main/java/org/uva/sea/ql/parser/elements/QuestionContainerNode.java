package org.uva.sea.ql.parser.elements;

import org.uva.sea.ql.QLExprEvaluate;

import java.util.List;

public interface QuestionContainerNode {
    List<Question> evalQuestions(QLExprEvaluate exprEvaluate);
}
