package ast.expression;

import ast.literal.Identifier;
import visiting.ExpressionVisitor;

public class IdentityExpression extends Expression {

	public IdentityExpression(Identifier id) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
