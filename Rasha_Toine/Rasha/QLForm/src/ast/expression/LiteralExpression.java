package ast.expression;

import visiting.ExpressionVisitor;
import ast.literal.Literal;

public class LiteralExpression extends Expression {

	private Literal<Object> obj;
	
	public LiteralExpression(Literal<Object> obj) {
		this.obj = obj;
	}

	public Literal<Object> getObj() {
		return obj;
	}

	public void setObj(Literal<Object> obj) {
		this.obj = obj;
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
