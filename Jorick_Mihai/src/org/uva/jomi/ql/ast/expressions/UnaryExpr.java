package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class UnaryExpr extends Expr {
	public final QLToken operator;
	public final Expr right;
	
	public UnaryExpr(QLToken operator, Expr right) {
		this.operator = operator;
		this.right = right;
	}

	@Override
	public
	<T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}