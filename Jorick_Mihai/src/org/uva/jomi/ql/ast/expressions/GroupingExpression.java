package org.uva.jomi.ql.ast.expressions;

public class GroupingExpression extends Expression {
	private final Expression expression;
	
	public GroupingExpression(Expression expression) {
		this.expression = expression;
	}

	public Expression getExpression() {
		return expression;
	}
	
	public <T> T visitInnerExpression(Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}
	
	public int getInnerExpressionId() {
		return this.expression.getNodeId();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}