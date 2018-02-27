package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class UnaryNotExpr extends UnaryExpr {

	public UnaryNotExpr(QLToken operator, Expr right) {
		super(operator, right);
	}

	@Override
	public
	<T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}