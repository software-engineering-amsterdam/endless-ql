package qlviz.typecheker;

import qlviz.model.Node;
import qlviz.model.question.Question;

public class CircularReferenceResult implements AnalysisResult {

    private final Node node;

    public CircularReferenceResult(Node node) {
        this.node = node;
    }

    @Override
    public String getDescription() {
        return "Circular dependency detected in the expression "
                + "on line "
                + node.getContext().getStart().getLine();
    }

    @Override
    public Severity getSeverity() {
        return Severity.Error;
    }
}
