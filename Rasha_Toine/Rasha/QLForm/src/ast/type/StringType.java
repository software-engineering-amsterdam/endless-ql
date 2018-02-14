package ast.type;

public class StringType extends Type {

	private String val;

	public StringType() {
		this.setVal("");
	}
	
	public StringType(String val) {
		this.setVal(val);
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}
}
