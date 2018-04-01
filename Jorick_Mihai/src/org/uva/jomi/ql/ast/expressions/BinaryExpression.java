package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public abstract class BinaryExpression extends Expression {
	private final Expression left;
	private final QLToken operator;
	private final Expression right;
	
	public BinaryExpression(Expression left, QLToken operator, Expression right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
		
		// Store the location of the left hand side expression for error messages.
		this.setLineNumber(left.getLineNumber());
		this.setColumnNumber(left.getColumnNumber());
	}
	
	public Expression getLeftExpression() {
		return left;
	}
	
	public QLType getLeftExpressionType() {
		return left.getType();
	}
	
	public void setLeftExpressionType(QLType type) {
		left.setType(type);
	}
	
	public Expression getRightExpression() {
		return right;
	}
	
	public QLType getRightExpressionType() {
		return right.getType();
	}
	
	public void setRightExpressionType(QLType type) {
		right.setType(type);
	}

	public QLToken getOperator() {
		return operator;
	}
	
	public String getOperatorName() {
		return operator.getLexeme();
	}
	
	public <T> T visitLeftExpression(Visitor<T> visitor) {
		return this.left.accept(visitor);
	}
	
	public <T> T visitRightExpression(Visitor<T> visitor) {
		return this.right.accept(visitor);
	}
	
	public int getLeftExpressionId() {
		return this.left.getNodeId();
	}
	
	public int getRightExpressionId() {
		return this.right.getNodeId();
	}
}