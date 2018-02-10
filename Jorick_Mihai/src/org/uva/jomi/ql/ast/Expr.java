package org.uva.jomi.ql.ast;

abstract public class Expr extends AstNode {
	interface Visitor<T> {
		T visitIndetifierExpr(IndentifierExpr expr);
		T visitPrimaryExpr(PrimaryExpr expr);
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