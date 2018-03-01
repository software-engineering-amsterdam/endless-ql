package org.uva.jomi.ql.error;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.statements.ComputedQuestionStmt;
import org.uva.jomi.ql.ast.statements.IfStmt;

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
	
	public void addTypeError(Expr expr, QLType...allowedTypes) {
		
		ArrayList<String> typeNames = new ArrayList<>();
		Arrays.asList(allowedTypes).forEach((type) -> typeNames.add(type.getName()));
		
		String message = String.format("[%s] line: %d, column: %d: Type mismatch, desired type: %s, allowed types: %s",
				moduleName,
				expr.getLineNumber(),
				expr.getColumnNumber(),
				expr.getType(),
				String.join(" or ", typeNames));
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addTypeError(Expr parentExpr, Expr childExpr) {
		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				childExpr.getLineNumber(),
				childExpr.getColumnNumber(),
				parentExpr.getType(),
				childExpr.getType());
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addTypeError(Expr leftExpr, QLToken operator, Expr rightExpr) {
		String message = String.format("[%s] line: %d, column: %d: Operator %s is undefined for types: %s and %s",
				moduleName,
				operator.getLine(),
				operator.getColumn(),
				operator.getLexeme(),
				leftExpr.getType(),
				rightExpr.getType());
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addTypeError(ComputedQuestionStmt stmt) {
		
		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				stmt.getExprLineNumber(),
				stmt.getExprColumnNumber(),
				stmt.getType(),
				stmt.getExprType());
		
		Error error = new TypeError(message);
		errorMessages.add(error);
		if (printErrors)
			System.err.println(error.toString());
	}
	
	public void addTypeError(IfStmt stmt) {
		
		String message = String.format("[%s] line: %d, column: %d: Type mismatch, expected %s, but got %s",
				moduleName,
				stmt.getExprLineNumber(),
				stmt.getExprColumnNumber(),
				QLType.BOOLEAN,
				stmt.getExprType());
		
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
