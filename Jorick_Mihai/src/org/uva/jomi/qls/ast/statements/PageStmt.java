package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class PageStmt extends Stmt {

	private IdentifierExpression identifier;
	private BlockStmt blockStmt;

	public PageStmt(IdentifierExpression identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
