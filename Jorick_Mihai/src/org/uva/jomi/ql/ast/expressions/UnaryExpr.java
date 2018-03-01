package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public abstract class UnaryExpr extends Expr {
	private final QLToken operator;
	private final Expr right;
	
	public UnaryExpr(QLToken operator, Expr right) {
		this.operator = operator;
		this.right = right;
	}

	public QLToken getOperator() {
		return this.operator;
	}

	public String getOperatorName() {
		return this.operator.getLexeme();
	}

	public Expr getRightExpr() {
		return this.right;
	}
	
	public QLType getRightExprType() {
		return this.right.getType();
	}
	
	public <T> T visitRightExpr(Visitor<T> visitor) {
		return this.right.accept(visitor);
	}
}