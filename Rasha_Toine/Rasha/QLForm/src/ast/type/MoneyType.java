package ast.type;

import java.math.BigDecimal;

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
}
