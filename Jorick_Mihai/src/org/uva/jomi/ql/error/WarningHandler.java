package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.statements.QuestionStmt;

public class WarningHandler {

	private final List<String> warningList;
	private final String moduleName;
	private final boolean printWarnings;
	
	public WarningHandler(String moduleName, boolean printWarnings) {
		this.warningList = new ArrayList<>();
		this.moduleName = moduleName;
		this.printWarnings = printWarnings;
		
	}
	
	public int getNumberOfErrors() {
		return warningList.size();
	}
	
	public void addWarning(QuestionStmt stmt, List<String> questionNames) {
		String warning = String.format("[%s] line: %d, column: %d: %s has the same label as questions: %s",
				moduleName,
				stmt.getIdentifierLineNumber(),
				stmt.getIdentifierColumnNumber(),
				stmt.getIdentifierName(),
				String.join(" and ", questionNames));
		
		if (printWarnings) {
			System.err.println(warning);
		}
				
		warningList.add(warning);
	}

	public String getErrorAtIndex(int index) {
		return warningList.get(index);
	}
}
