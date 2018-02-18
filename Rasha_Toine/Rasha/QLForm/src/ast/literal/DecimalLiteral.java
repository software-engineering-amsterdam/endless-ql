package ast.literal;

import visiting.LiteralVisitor;

public class DecimalLiteral extends Literal<Double>{

	public DecimalLiteral(double value) {
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
