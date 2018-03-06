package qlviz.typecheker;

import qlviz.model.question.Question;

import java.util.List;

public interface AnalysisResult {
    String getDescription();
    Severity getSeverity();
}

