package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class OrExpression extends BinaryExpression {
	
	public OrExpression(Expression left, QLToken operator, Expression right) {
		super(left, operator, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}