package ast;

import utils.CodeReference;

public abstract class AstNode {
	
	CodeReference location;

	public AstNode() {
		this.location = null;
	}

	public AstNode(CodeReference location) {
		this.location = location;
	}
}