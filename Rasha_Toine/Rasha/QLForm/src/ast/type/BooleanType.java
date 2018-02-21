package ast.type;

import visiting.TypeVisitor;

public class BooleanType extends Type {
	
	private boolean val;

	public BooleanType() {

	}
	
	public BooleanType(boolean val) {
		this.setVal(val);
	}

	public boolean getVal() {
		return val;
	}

	public void setVal(boolean val) {
		this.val = val;
	}

	@Override
	public <T, U> T accept(TypeVisitor<T, U> visitor, U ctx) {
		// TODO Auto-generated method stub
		//return null;
		return visitor.visit(this, ctx);
	}
}
