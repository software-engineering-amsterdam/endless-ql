package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.IdentifierExpr;
import org.uva.jomi.ql.ast.statements.Stmt.Visitor;

public class FormStmt extends Stmt {
	public final IdentifierExpr identifier;
	public final BlockStmt blockStmt;
	
	public FormStmt(IdentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}