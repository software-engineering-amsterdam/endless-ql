package ast.type;

import visiting.TypeVisitor;

public class IntegerType extends Type {

    private int val;
    
    public IntegerType() {
        this.setVal(0);
    }
    
    public IntegerType(int val) {
        this.setVal(val);
    }


	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
	
	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
