package ast.expression;

import visiting.ExpressionVisitor;
import ast.literal.Literal;

public class LiteralExpression extends Expression {

	private Literal literal;
	
	public LiteralExpression(Literal literal) {
		this.setLiteral(literal);
	}

	@Override
	public <T, U> T accept(ExpressionVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}

	public Literal getLiteral() {
		return literal;
	}

	public void setLiteral(Literal literal) {
		this.literal = literal;
	}
}
