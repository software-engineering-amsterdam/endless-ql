package org.uva.jomi.ql.ast.expressions;

public class LessThanOrEqualExpr extends Expr {
	
	public final Expr left;
	public final Expr right;
	
	public LessThanOrEqualExpr(Expr left, Expr right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}