package ast;

import ast.AstNode;
import ast.expression.Identifier;
import visiting.QLTreeVisitor;

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
	
	public <T, U> T accept(QLTreeVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}