package org.uva.jomi.ql.ast.expressions;

public class GroupingExpr extends Expr {
	private final Expr expression;
	
	public GroupingExpr(Expr expression) {
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}