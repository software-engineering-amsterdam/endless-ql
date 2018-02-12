package org.uva.jomi.ql.ast;

public class IfStmt extends Stmt {
	final Expr expression;
	final BlockStmt blockStmt;
	
	public IfStmt(Expr expression, BlockStmt blockStmt) {
		this.expression = expression;
		this.blockStmt = blockStmt;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitIfStmt(this);
	}
}