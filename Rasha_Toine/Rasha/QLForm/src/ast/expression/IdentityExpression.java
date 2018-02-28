package ast.expression;

import visiting.ExpressionVisitor;

public class IdentityExpression extends Expression {
	
	private String name;

	public IdentityExpression() {
		// TODO Auto-generated constructor stub
	}
	
	public IdentityExpression(String name) {
		this.name = name;
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
