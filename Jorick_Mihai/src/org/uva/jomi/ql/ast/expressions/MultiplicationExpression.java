package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class MultiplicationExpression extends BinaryExpression {

	public MultiplicationExpression(Expression left, QLToken operator, Expression right) {
		super(left, operator, right);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}