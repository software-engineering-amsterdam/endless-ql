package qlviz.gui.renderer;

import qlviz.typecheker.AnalysisResult;

import java.util.List;

public interface ErrorRenderer {
    void render(List<AnalysisResult> analysisResults);
}
