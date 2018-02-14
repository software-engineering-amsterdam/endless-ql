package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.IndentifierExpr;
import org.uva.jomi.ql.ast.statements.Stmt.Visitor;

public class FormStmt extends Stmt {
	public final IndentifierExpr identifier;
	public final BlockStmt blockStmt;
	
	public FormStmt(IndentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitFormStmt(this);
	}
}