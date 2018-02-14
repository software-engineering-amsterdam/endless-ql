package ast.type;

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
}
