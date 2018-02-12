package org.uva.jomi.ql.ast;

public class PrimaryExpr extends Expr {
	final QLToken token;

	public PrimaryExpr(QLToken token) {
		this.token = token;
	}
	
	public PrimaryExpr(QLToken token, QLType type) {
		this.token = token;
		this.setType(type);
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitPrimaryExpr(this);
	}
}