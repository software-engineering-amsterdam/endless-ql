package org.uva.jomi.ql.ast.statements;

import java.util.List;

public class BlockStmt extends Stmt {
	private final List<Stmt> statements;
	
	public BlockStmt(List<Stmt> statements) {
		this.statements = statements;
	}

	public List<Stmt> getStatements() {
		return statements;
	}
	
	public Stmt getStmtAtIndex(int index) {
		return this.statements.get(index);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}