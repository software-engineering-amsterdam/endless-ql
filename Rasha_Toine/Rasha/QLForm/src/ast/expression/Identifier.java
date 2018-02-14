package ast.expression;

public class Identifier extends Expression {

	private final String id;

	public Identifier(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return id;
	}
}