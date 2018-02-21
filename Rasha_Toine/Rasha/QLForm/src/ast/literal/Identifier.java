package ast.literal;

import ast.AstNode;

public class Identifier extends AstNode {

	private final String id;

	public Identifier(String id) {
		this.id = id;
	}

	public String getIdentifier() {
		return id;
	}
}