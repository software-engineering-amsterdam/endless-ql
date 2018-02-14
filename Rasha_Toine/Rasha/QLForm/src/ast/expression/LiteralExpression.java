package ast.expression;

import ast.literal.Literal;

public class LiteralExpression extends Expression {
	
	private Literal<Object> obj;

	public Literal<Object> getObj() {
		return obj;
	}

	public void setObj(Literal<Object> obj) {
		this.obj = obj;
	}
}
