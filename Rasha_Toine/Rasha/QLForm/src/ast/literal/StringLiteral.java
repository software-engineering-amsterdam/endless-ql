package ast.literal;

import visiting.LiteralVisitor;

public class StringLiteral extends Literal<String> {

	public StringLiteral(String value) {
		super(value);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
