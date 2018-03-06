package ql.ast.expression;

import ql.visiting.ExpressionVisitor;

public class Sub extends BinaryExpression {

	public Sub(Expression left, Expression right) {
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
