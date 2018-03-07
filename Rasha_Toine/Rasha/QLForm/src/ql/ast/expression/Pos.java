package ql.ast.expression;

import ql.visiting.ExpressionVisitor;

public class Pos extends UnaryExpression {

	public Pos(Expression expr) {
		super(expr);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
