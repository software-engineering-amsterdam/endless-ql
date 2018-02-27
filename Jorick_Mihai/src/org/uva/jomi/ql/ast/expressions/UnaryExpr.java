package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public abstract class UnaryExpr extends Expr {
	private final QLToken operator;
	private final Expr right;
	
	public UnaryExpr(QLToken operator, Expr right) {
		this.operator = operator;
		this.right = right;
	}

	public QLToken getOperator() {
		return operator;
	}

	public String getOperatorName() {
		return operator.getLexeme();
	}

	public Expr getRightExpr() {
		return right;
	}
}