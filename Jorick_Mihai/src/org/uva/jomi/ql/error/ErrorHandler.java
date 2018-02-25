package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;

public class ErrorHandler {
	
	private class Error {
		private final String message;
		
		public Error(String message) {
			this.message = message;
		}
		
		public String getMessage() {
			return message;
		}
	}
	
	private class TypeError extends Error {

		public TypeError(String message) {
			super(message);
		}
		
		@Override
		public String toString() {
			return super.getMessage();
		}
	}
	
	private class IdentifierError extends Error {


		private final String name;
		private final int line;
		private final int column;
		
		public IdentifierError(String name, int line, int column, String message) {
			super(message);
			this.name = name;
			this.line = line;
			this.column = column;
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

	public void addIdentifierError(QLToken token, String message) {
		Error error = new IdentifierError(token.getLexeme(), token.getLine(), token.getColumn(), message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addTypeError(Expr main, Expr left, Expr right) {
		
		String message = String.format("Type missmatch, expected %s, but got %s and %s",
				main.getType(),
				left.getType(),
				right.getType());
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	
	// this.errorHandler.addTypeError(stmt.label + " expected " + stmt.getType() + " but got " + stmt.expression.getType());
	public void addTypeError(ComputedQuestionStmt stmt) {
		
		String message = String.format("%s, expected %s, but got %s and %s",
				stmt.getIdentifier(),
				stmt.getType(),
				stmt.expression.getType());
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addError(String errorMessage) {
		Error error = new Error(errorMessage);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void clearErrors() {
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
