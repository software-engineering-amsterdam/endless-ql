package ast;

import java.util.List;
import java.util.Set;

import org.antlr.runtime.tree.TreeWizard.Visitor;

import ast.AstNode;

public class Form extends AstNode{

	private final String id;
	private final Block block;

	public Form(String id, Block block) {
		this.id = id;
		this.block = block;
	}

	public String getId() {
		return id;
	}

	public Block getBlock() {
		return this.block;
	}
	

}