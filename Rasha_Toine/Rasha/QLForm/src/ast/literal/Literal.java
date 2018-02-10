package ast.literal;

public abstract class Literal<T> {

	private T val;
	
	public Literal(T val) {
		this.setValue(val);
	}
	
	public T getValue() {
		return val;
	}

	public void setValue(T val) {
		this.val = val;
	}
}
