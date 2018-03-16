package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class FormStmt extends Stmt {
	private final IdentifierExpression identifier;
	private final BlockStmt blockStmt;
	
	public FormStmt(IdentifierExpression identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	public IdentifierExpression getIdentifier() {
		return this.identifier;
	}
	
	public String getIdentifierName() {
		return this.identifier.getName();
	}
	
	public int getIndetifierExprId() {
		return this.identifier.getNodeId();
	}

	public BlockStmt getBlockStmt() {
		return this.blockStmt;
	}
	
	public int getBlockStmtId() {
		return this.blockStmt.getNodeId();
	}
	
	public <T> T visitIndetifierExpr(Expression.Visitor<T> visitor) {
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