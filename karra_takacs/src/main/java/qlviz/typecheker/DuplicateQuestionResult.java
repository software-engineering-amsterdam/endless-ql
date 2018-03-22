package qlviz.typecheker;

import qlviz.model.question.Question;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateQuestionResult implements AnalysisResult {

    private final List<Question> instances;

    public DuplicateQuestionResult(List<Question> instances) {
        this.instances = instances;
    }

    @Override
    public String getDescription() {
        return "Duplicate questions detected on lines: " +
                String.join(", ",
                        instances
                                .stream()
                                .map(question -> ((Integer)question.getQuestionContext().getStart().getLine()).toString())
                                .collect(Collectors.toList()));
    }

    @Override
    public Severity getSeverity() {
        return Severity.Error;
    }
}
