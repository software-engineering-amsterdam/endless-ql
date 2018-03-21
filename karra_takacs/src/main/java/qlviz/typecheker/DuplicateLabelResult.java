package qlviz.typecheker;

import qlviz.model.question.Question;

import java.util.List;
import java.util.stream.Collectors;

public class DuplicateLabelResult implements AnalysisResult {

    private final List<Question> instances;

    public DuplicateLabelResult(List<Question> instances) {
        this.instances = instances;
    }

    @Override
    public String getDescription() {
        return "Duplicate Labels detected on "+
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
