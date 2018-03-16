package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public abstract class UnaryExpression extends Expression {
	private final QLToken operator;
	private final Expression right;
	
	public UnaryExpression(QLToken operator, Expression right) {
		this.operator = operator;
		this.right = right;
	}

	public QLToken getOperator() {
		return this.operator;
	}

	public String getOperatorName() {
		return this.operator.getLexeme();
	}

	public Expression getRightExpression() {
		return this.right;
	}
	
	public QLType getRightExpressionType() {
		return this.right.getType();
	}
	
	public <T> T visitRightExpression(Visitor<T> visitor) {
		return this.right.accept(visitor);
	}
}