package ast.literal;

import checking.value.IntegerValue;
import visiting.LiteralVisitor;

public class IntegerLiteral extends Literal{

    private IntegerValue value;
    
    public IntegerLiteral(int value) {
    	this.value = new IntegerValue(value);
    }
    
    @Override
	public IntegerValue getValue() {
		return value;
	}

	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}

	
}
