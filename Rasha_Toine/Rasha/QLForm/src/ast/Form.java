package ast;

import ast.AstNode;
import ast.expression.Identifier;

public class Form extends AstNode{

	private final Identifier id;
	private final Block block;

	public Form(Identifier id, Block block) {
		this.id = id;
		this.block = block;
	}

	public Identifier getId() {
		return id;
	}

	public Block getBlock() {
		return this.block;
	}
	
}