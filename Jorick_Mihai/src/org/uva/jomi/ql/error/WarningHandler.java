package org.uva.jomi.ql.error;

import java.util.List;

import org.uva.jomi.ql.ast.statements.QuestionStmt;

public class WarningHandler extends ErrorReporter<String> {

	private final String moduleName;
	private final boolean printWarnings;
	
	public WarningHandler(String moduleName, boolean printWarnings) {
		this.moduleName = moduleName;
		this.printWarnings = printWarnings;
	}
	
	public int getNumberOfWarnings() {
		return this.getNumberOfReports();
	}
	
	public void addWarning(QuestionStmt stmt, List<String> questionNames) {
		String warning = String.format("[%s] line: %d, column: %d: %s has the same label as questions: %s",
				moduleName,
				stmt.getIdentifierLineNumber(),
				stmt.getIdentifierColumnNumber(),
				stmt.getName(),
				String.join(" and ", questionNames));
		
		if (printWarnings) {
			System.err.println(warning);
		}
				
		this.addReport(warning);
	}

	public String getError(int index) {
		return this.getReport(index);
	}
}
