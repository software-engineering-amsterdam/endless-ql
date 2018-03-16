package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;

public class IfStmt extends Stmt {
	private final Expression expression;
	private final BlockStmt ifBlockStmt;
	
	public IfStmt(Expression expression, BlockStmt ifBlockStmt) {
		this.expression = expression;
		this.ifBlockStmt = ifBlockStmt;
	}

	public Expression getExpr() {
		return this.expression;
	}
	
	public int getExprLineNumber() {
		return this.expression.getLineNumber();
	}
	
	public int getExprColumnNumber() {
		return this.expression.getColumnNumber();
	}
	
	public QLType getExprType() {
		return this.expression.getType();
	}
	
	public int getExprId() {
		return this.ifBlockStmt.getNodeId();
	}

	public BlockStmt getIfBlockStmt() {
		return this.ifBlockStmt;
	}
	
	public int getIfBlockStmtId() {
		return this.ifBlockStmt.getNodeId();
	}
	
	public <T> T visitExpr(Expression.Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}
	
	public <T> T visitIfBlockStmt(Visitor<T> visitor) {
		return this.ifBlockStmt.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}