package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public class PrimaryExpr extends Expr {
	private final String lexeme;

	public PrimaryExpr(QLToken token, QLType type) {
		this.lexeme = token.getLexeme();
		this.setType(type);
		this.setLineNumber(token.getLine());
		this.setColumnNumber(token.getColumn());
	}

	public String getLexeme() {
		return lexeme;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}