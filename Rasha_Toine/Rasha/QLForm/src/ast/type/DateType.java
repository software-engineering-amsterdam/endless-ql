package ast.type;

import java.util.Date;

public class DateType extends Type {
    
    private Date val;
    
    public DateType(){
    	this.setVal(new Date());
    }
    
    public DateType(Date val) {
        this.setVal(val);
    }

	public Date getVal() {
		return val;
	}

	public void setVal(Date val) {
		this.val = val;
	}
}
