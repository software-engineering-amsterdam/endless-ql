package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expr;

public class IfStmt extends Stmt {
	public final Expr expression;
	public final BlockStmt blockStmt;
	
	public IfStmt(Expr expression, BlockStmt blockStmt) {
		this.expression = expression;
		this.blockStmt = blockStmt;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitIfStmt(this);
	}
}