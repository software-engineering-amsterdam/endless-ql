package ast;

import java.util.List;
import java.util.Set;

import ast.AstNode;

public class Form extends AstNode{

	private final String id;
	private final List<Block> blocks;

	public Form(String id, List<Block> blocks) {
		this.id = id;
		this.blocks = blocks;
	}

	public String getId() {
		return id;
	}

	public List<Block> getBlocks() {
		return this.blocks;
	}
}