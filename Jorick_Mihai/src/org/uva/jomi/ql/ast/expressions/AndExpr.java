package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class AndExpr extends Expr {
	private final Expr left;
	private final Expr right;
	private final QLToken operator;	
	
	public AndExpr(Expr left, QLToken operator, Expr right) {
		this.left = left;
		this.right = right;
		this.operator = operator;
	}

	public Expr getLeftExpr() {
		return left;
	}

	public Expr getRightExpr() {
		return right;
	}

	public QLToken getOperator() {
		return operator;
	}
	
	public String getOperatorName() {
		return operator.getLexeme();
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}