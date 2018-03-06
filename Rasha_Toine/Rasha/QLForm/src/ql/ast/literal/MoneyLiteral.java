package ql.ast.literal;

import java.math.BigDecimal;

import ql.visiting.LiteralVisitor;
import ql.visiting.value.DecimalValue;

public class MoneyLiteral extends Literal {

    private DecimalValue value;
    
    public MoneyLiteral(BigDecimal value) {
    	this.value = new DecimalValue(value);
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
