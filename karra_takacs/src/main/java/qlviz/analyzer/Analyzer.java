package qlviz.analyzer;

import qlviz.model.Form;

import java.util.List;

public interface Analyzer {
    void initialize(Form form);
    List<AnalysisResult> analyze();
}
