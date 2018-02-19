package ast.literal;

import java.math.BigDecimal;

import visiting.LiteralVisitor;

public class MoneyLiteral extends Literal<BigDecimal> {

	public MoneyLiteral(BigDecimal value) {
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
