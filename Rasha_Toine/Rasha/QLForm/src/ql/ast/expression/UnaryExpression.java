package ql.ast.expression;

public abstract class UnaryExpression extends Expression {

	private Expression expr;

	public UnaryExpression(Expression exp){
		this.setExpression(exp);
	}

	public Expression getExpression() {
		return expr;
	}

	public void setExpression(Expression expr) {
		this.expr = expr;
	}
}
