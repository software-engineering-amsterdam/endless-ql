package ast.expression;

import visiting.ExpressionVisitor;

public class GEq extends BinaryExpression {

	public GEq(Expression left, Expression right) {
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