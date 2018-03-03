package qlviz.typecheker;

import qlviz.model.question.Question;

import java.util.List;

public class DuplicateQuestionResult implements AnalysisResult {

    private final List<Question> instances;

    public DuplicateQuestionResult(List<Question> instances) {
        this.instances = instances;
    }

    @Override
    public String getDescription() {
        return "Duplicate questions detected.";
    }

    @Override
    public Severity getSeverity() {
        return Severity.Error;
    }
}
