package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class FormStmt extends Stmt {
	private final IdentifierExpr identifier;
	private final BlockStmt blockStmt;
	
	public FormStmt(IdentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	public IdentifierExpr getIdentifier() {
		return this.identifier;
	}
	
	public String getIdentifierName() {
		return this.identifier.getName();
	}
	
	public int getIndetifierExprId() {
		return this.identifier.getId();
	}

	public BlockStmt getBlockStmt() {
		return this.blockStmt;
	}
	
	public int getBlockStmtId() {
		return this.blockStmt.getId();
	}
	
	public <T> T visitIndetifierExpr(Expr.Visitor<T> visitor) {
		return this.identifier.accept(visitor);
	}
	
	public <T> T visitBlockStmt(Visitor<T> visitor) {
		return this.blockStmt.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}