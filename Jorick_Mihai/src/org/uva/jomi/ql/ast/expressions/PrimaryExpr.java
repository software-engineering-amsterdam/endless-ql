package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr.Visitor;

public class PrimaryExpr extends Expr {
	public final QLToken token;

	public PrimaryExpr(QLToken token) {
		this.token = token;
	}
	
	public PrimaryExpr(QLToken token, QLType type) {
		this.token = token;
		this.setType(type);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}