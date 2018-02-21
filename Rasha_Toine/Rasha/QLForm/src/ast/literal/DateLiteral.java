package ast.literal;

import java.util.Date;

import visiting.LiteralVisitor;

public class DateLiteral extends Literal<Date>{

	public DateLiteral(Date value) {
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
