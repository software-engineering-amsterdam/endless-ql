package ql.ast.expression;

import ql.visiting.ExpressionVisitor;

public class LEq extends BinaryExpression {

	public LEq(Expression left, Expression right) {
		super(left, right);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}