package ql.exceptions;

import ql.ast.statement.Question;

public class DuplicateLabelWarning extends QLException {

    public DuplicateLabelWarning(Question question) {
        
        message     = "Duplicate label [\""+question.getLabel()+"\"]";
        location    = question.getLocation();
    }
}
