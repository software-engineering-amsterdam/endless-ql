package org.uva.jomi.ql.ast.expressions;

public class GreaterThanExpr extends Expr {
	
	public final Expr left;
	public final Expr right;
	
	public GreaterThanExpr(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}