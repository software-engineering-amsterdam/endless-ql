package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public abstract class PrimaryExpression extends Expression {
	private final String lexeme;

	public PrimaryExpression(QLToken token, QLType type) {
		this.lexeme = token.getLexeme();
		this.setType(type);
		this.setLineNumber(token.getLine());
		this.setColumnNumber(token.getColumn());
	}

	public String getLexeme() {
		return lexeme;
	}
}