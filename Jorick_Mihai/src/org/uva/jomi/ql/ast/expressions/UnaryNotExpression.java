package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class UnaryNotExpression extends UnaryExpression {

	public UnaryNotExpression(QLToken operator, Expression right) {
		super(operator, right);
	}

	@Override
	public
	<T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}