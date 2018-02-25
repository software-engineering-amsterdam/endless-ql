package ast.literal;

import visiting.LiteralVisitor;

public class IntegerLiteral extends Literal<Integer>{

	public IntegerLiteral(Integer value) {
		super(value);
	}

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}
