package ast;

import java.util.List;

import ast.statement.Statement;
import visiting.QLTreeVisitor;


public class Block extends AstNode {

	private final List<Statement> statements;

	public Block(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}
	
	public <T, U> T accept(QLTreeVisitor<T, U> visitor, U ctx) {
		return visitor.visit(this, ctx);
	}
}
