package org.uva.jomi.ql.ast.expressions;

public class UnaryNotExpr extends Expr {
	public final Expr right;
	
	public UnaryNotExpr(Expr right) {
		this.right = right;
	}

	@Override
	public
	<T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}