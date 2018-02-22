package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expr;

public class IfElseStmt extends Stmt {
	public final Expr expression;
	public final BlockStmt ifBlockStmt;
	public final BlockStmt elseBlockStmt;
	
	public IfElseStmt(Expr expression, BlockStmt ifBlockStmt, BlockStmt elseBlockStmt) {
		this.expression = expression;
		this.ifBlockStmt = ifBlockStmt;
		this.elseBlockStmt = elseBlockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitIfElseStmt(this);
	}
}