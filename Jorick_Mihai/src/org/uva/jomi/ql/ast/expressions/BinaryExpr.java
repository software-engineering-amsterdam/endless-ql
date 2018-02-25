package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class BinaryExpr extends Expr {
	private final Expr left;
	private final QLToken operator;
	private final Expr right;
	
	public BinaryExpr(Expr left, QLToken operator, Expr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	public Expr getLeftExpr() {
		return left;
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

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}