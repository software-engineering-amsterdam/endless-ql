package qlviz.gui.renderer;

import qlviz.analyzer.AnalysisResult;

import java.util.List;

public interface ErrorRenderer {
    void render(List<AnalysisResult> analysisResults);
}
