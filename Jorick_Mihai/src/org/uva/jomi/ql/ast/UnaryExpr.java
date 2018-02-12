package org.uva.jomi.ql.ast;

public class UnaryExpr extends Expr {
	final QLToken operator;
	final Expr right;
	
	public UnaryExpr(QLToken operator, Expr right) {
		this.operator = operator;
		this.right = right;
	}

	@Override
	public
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitUnaryExpr(this);
	}
}