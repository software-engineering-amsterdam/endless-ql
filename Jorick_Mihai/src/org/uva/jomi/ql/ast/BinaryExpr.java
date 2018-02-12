package org.uva.jomi.ql.ast;

public class BinaryExpr extends Expr {
	final Expr left;
	final QLToken operator;
	final Expr right;
	
	public BinaryExpr(Expr left, QLToken operator, Expr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitBinaryExpr(this);
	}
}