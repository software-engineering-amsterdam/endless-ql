package qlviz.typecheker;

import qlviz.model.question.Question;

public class CircularReferenceResult implements AnalysisResult {

    private final Question question;

    public CircularReferenceResult(Question question) {
        this.question = question;
    }

    @Override
    public String getDescription() {
        return "Circular dependency detected in the expression of question \""
                + question.getName()
                + "\" on line "
                + question.getQuestionContext().getStart().getLine();
    }

    @Override
    public Severity getSeverity() {
        return Severity.Error;
    }
}
