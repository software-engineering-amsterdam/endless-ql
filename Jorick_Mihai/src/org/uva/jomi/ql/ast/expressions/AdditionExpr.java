package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;

public class AdditionExpr extends Expr {
	
	private final Expr left;
	private final Expr right;
	private final QLToken operator;
	
	public AdditionExpr(Expr left, QLToken operator, Expr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
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
