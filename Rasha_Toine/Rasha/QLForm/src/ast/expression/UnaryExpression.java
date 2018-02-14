package ast.expression;

public class UnaryExpression extends Expression {

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
