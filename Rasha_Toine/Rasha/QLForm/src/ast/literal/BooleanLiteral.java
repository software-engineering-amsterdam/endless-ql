package ast.literal;

import visiting.LiteralVisitor;

public class BooleanLiteral extends Literal<Boolean>{

    public static final BooleanLiteral TRUE = new BooleanLiteral(true);
    public static final BooleanLiteral FALSE = new BooleanLiteral(false);

	public BooleanLiteral(Boolean value) {
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
