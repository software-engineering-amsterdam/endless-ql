package ast.literal;

import visiting.LiteralVisitor;
import visiting.value.BooleanValue;

public class BooleanLiteral extends Literal{

	private BooleanValue value;
	
    public static final BooleanLiteral TRUE = new BooleanLiteral(true);
    public static final BooleanLiteral FALSE = new BooleanLiteral(false);
    

    public BooleanLiteral(boolean value){
    	this.value = new BooleanValue(value);
    }

    @Override
    public BooleanValue getValue(){
    	return value;
    }

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}
