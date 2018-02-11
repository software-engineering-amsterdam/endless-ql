package ast;

import java.util.List;

import ast.statement.Statement;


public class Block extends AstNode {

	private final List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}
}
