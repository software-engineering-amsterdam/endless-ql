package org.uva.jomi.ql.ast;

public class GroupingExpr extends Expr {
	final Expr expression;
	
	public GroupingExpr(Expr expression) {
		this.expression = expression;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitGroupingExpr(this);
	}
}