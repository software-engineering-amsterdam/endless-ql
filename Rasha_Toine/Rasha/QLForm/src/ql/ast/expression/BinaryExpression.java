package ql.ast.expression;

public abstract class BinaryExpression extends Expression {
	private Expression left;
	private Expression right;
	
	public BinaryExpression(Expression left, Expression right) {
		this.setLeft(left);
		this.setRight(right);
	}

	public Expression getLeft() {
		return left;
	}

	public void setLeft(Expression left) {
		this.left = left;
	}

	public Expression getRight() {
		return right;
	}

	public void setRight(Expression right) {
		this.right = right;
	}
}
