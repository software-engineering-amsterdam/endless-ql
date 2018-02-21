package ast.expression;

import visiting.ExpressionVisitor;

public class ParenthesesExpression extends UnaryExpression {

	public ParenthesesExpression(Expression expr) {
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
