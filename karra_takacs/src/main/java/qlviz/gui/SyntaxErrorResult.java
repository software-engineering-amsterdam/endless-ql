package qlviz.gui;

import qlviz.typecheker.AnalysisResult;
import qlviz.typecheker.Severity;

public class SyntaxErrorResult implements AnalysisResult {


	private final int line;
    private final int col;
    private final String description;

    public SyntaxErrorResult(int line, int col, String description) {

		this.line = line;
        this.col = col;
        this.description = description;
    }

	@Override
	public String getDescription() {
	    return "Syntax error on line " + line + " column " + col + ": " + description;
	}

	@Override
	public Severity getSeverity() {
		return Severity.Error;
	}
}
