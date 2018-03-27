package qlviz.gui;

import qlviz.analyzer.AnalysisResult;

import java.util.List;

public class ParserException extends Exception {

	private final List<AnalysisResult> analysisResults;

	public ParserException(List<AnalysisResult> analysisResults) {

		this.analysisResults = analysisResults;
	}

	public List<AnalysisResult> getAnalysisResults() {
		return analysisResults;
	}
}
