package org.uva.jomi.ql.ast.expressions;

public class GroupingExpr extends Expr {
	private final Expr expression;
	
	public GroupingExpr(Expr expression) {
		this.expression = expression;
	}

	public Expr getExpression() {
		return expression;
	}
	
	public <T> T visitInnerExpr(Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}
	
	public int getInnerExprId() {
		return this.expression.getId();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}