package org.uva.sc.cr.ql.util;

public enum Operation {

	AND("&&"), OR("||"), EQUALS("=="), NOT_EQUALS("!="), SMALLER_THAN("<"), SMALLER_THAN_EQUALS("<="), GREATER_THAN(
			">"), GREATER_THAN_EQUALS(">="), PLUS("+"), MINUS("-"), MULTIPLICATION("*"), DIVISION("/"), NOT("!");

	private String literal;

	Operation(String literal) {
		this.literal = literal;
	}

	public String getLiteral() {
		return literal;
	}
}
