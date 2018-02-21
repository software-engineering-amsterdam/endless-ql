package ast.type;

import java.util.Date;

import visiting.TypeVisitor;

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
	
	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
