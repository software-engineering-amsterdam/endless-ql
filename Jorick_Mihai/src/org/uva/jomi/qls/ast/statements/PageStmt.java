package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class PageStmt extends Stmt {

	private IdentifierExpr identifier;
	private BlockStmt blockStmt;

	public PageStmt(IdentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
