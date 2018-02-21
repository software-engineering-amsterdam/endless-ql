package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.expressions.Expr.Visitor;

public class GroupingExpr extends Expr {
	public final Expr expression;
	
	public GroupingExpr(Expr expression) {
		this.expression = expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitGroupingExpr(this);
	}
}