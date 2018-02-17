package ast.literal;

import visiting.LiteralVisitor;


public abstract class Literal<T> {

	private T value;
	
	public Literal(T value) {
		this.setValue(value);
	}
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}
	
	public abstract <T, U> T accept(LiteralVisitor<T, U> visitor, U ctx);
}
