package org.uva.jomi.ql.ast.statements;

import java.util.List;

public class BlockStmt extends Stmt {
	public final List<Stmt> statements;
	
	public BlockStmt(List<Stmt> statements) {
		this.statements = statements;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitBlockStmt(this);
	}
}