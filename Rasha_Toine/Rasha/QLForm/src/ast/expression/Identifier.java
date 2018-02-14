package ast.expression;

import utils.CodeReference;

public class Identifier extends Expression {

	private final String id;

	public Identifier(String id, CodeReference location) {
		super(location);
		this.id = id;
	}

	public String getIdentifier() {
		return id;
	}
}