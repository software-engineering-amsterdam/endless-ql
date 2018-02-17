package ast.type;

import java.math.BigDecimal;

import visiting.TypeVisitor;

public class MoneyType extends Type {

    private BigDecimal val;
    
    public MoneyType(BigDecimal val) {
        this.setVal(val);
    }

	public BigDecimal getVal() {
		return val;
	}

	public void setVal(BigDecimal val) {
		this.val = val;
	}
	
	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
