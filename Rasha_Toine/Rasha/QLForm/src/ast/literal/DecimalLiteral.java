package ast.literal;

import java.math.BigDecimal;

import checking.value.DecimalValue;
import visiting.LiteralVisitor;

public class DecimalLiteral extends Literal{

    private DecimalValue value;
    
    public DecimalLiteral(BigDecimal value) {
    	this.value = new DecimalValue(value);
    }
    
    public DecimalLiteral(double value) {
    	this.value = new DecimalValue(BigDecimal.valueOf(value));
    }
    
    @Override
    public DecimalValue getValue() {
		return value;
	}


	@Override
	public <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}
