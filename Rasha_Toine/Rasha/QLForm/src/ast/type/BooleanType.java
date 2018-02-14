package ast.type;

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
}
