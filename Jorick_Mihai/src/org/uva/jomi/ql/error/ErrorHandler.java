package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;

public class ErrorHandler {
	
	private class Error {
		private final String name;
		private final int line;
		private final int column;
		private final String message;
		
		public Error(String name, int line, int column, String message) {
			this.name = name;
			this.line = line;
			this.column = column;
			this.message = message;
		}
		
		public String getName() {
			return name;
		}

		public int getLine() {
			return line;
		}

		public int getColumn() {
			return column;
		}

		public String getMessage() {
			return message;
		}

		@Override
		public String toString() {
			return String.format("[%s] line: %d, column: %d: %s: %s", moduleName,
					getLine(),
					getColumn(),
					getMessage(),
					getName());
		}
	}
	
	private final String moduleName;
	private List<Error> errorMessages;
	private boolean printErrors;
	
	public ErrorHandler(String moduleName, boolean printErrors) {
		this.moduleName = moduleName;
		this.printErrors = printErrors;
		errorMessages = new ArrayList<>();
	}
	
	public int getNumberOfErrors() {
		return errorMessages.size();
	}

	public void addError(QLToken token, String errorMessage) {
		Error error = new Error(token.getLexeme(), token.getLine(), token.getColumn(), errorMessage);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void clearErros() {
		errorMessages.clear();
	}
	
	public String getErrorAtIndex(int index) {
		if (index >= 0 && index < errorMessages.size()) {
			return errorMessages.get(index).toString();
		} else {
			return String.format("No error is present at index %d", index);
		}
	}
}
