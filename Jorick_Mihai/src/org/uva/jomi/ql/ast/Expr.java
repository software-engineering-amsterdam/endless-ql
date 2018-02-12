package org.uva.jomi.ql.ast;

abstract public class Expr extends AstNode {
	interface Visitor<T> {
		T visitIndetifierExpr(IndentifierExpr expr);
		T visitPrimaryExpr(PrimaryExpr expr);
		T visitBinaryExpr(BinaryExpr expr);
		T visitGroupingExpr(GroupingExpr expr);
		T visitUnaryExpr(UnaryExpr expr);
	}
	
	abstract <T> T accept(Visitor<T> visitor);
	
	private QLType type;
	
	public QLType getType() {
		return type;
	}
	
	public void setType(QLType type) {
		this.type = type;
	}
}

class IndentifierExpr extends Expr {
	final QLToken token;
	private boolean undefined = true;

	public IndentifierExpr(QLToken token) {
		this.token = token;
	}
	
	public IndentifierExpr(QLToken token, QLType type) {
		this.setType(type);
		this.token = token;
	}
	public boolean isUndefined() {
		return undefined;
	}

	public void setUndefined(boolean undefined) {
		this.undefined = undefined;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitIndetifierExpr(this);
	}
}

class PrimaryExpr extends Expr {
	final QLToken token;

	public PrimaryExpr(QLToken token) {
		this.token = token;
	}
	
	public PrimaryExpr(QLToken token, QLType type) {
		this.token = token;
		this.setType(type);
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitPrimaryExpr(this);
	}
}

class BinaryExpr extends Expr {
	final Expr left;
	final QLToken operator;
	final Expr right;
	
	public BinaryExpr(Expr left, QLToken operator, Expr right) {
		this.left = left;
		this.operator = operator;
		this.right = right;
	}
	
	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitBinaryExpr(this);
	}
}

class GroupingExpr extends Expr {
	final Expr expression;
	
	public GroupingExpr(Expr expression) {
		this.expression = expression;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitGroupingExpr(this);
	}
}

class UnaryExpr extends Expr {
	final QLToken operator;
	final Expr right;
	
	public UnaryExpr(QLToken operator, Expr right) {
		this.operator = operator;
		this.right = right;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitUnaryExpr(this);
	}
}