package org.uva.jomi.ql.ast;

abstract public class Expr {
	interface Visitor<T> {
		T visitIndetifierExpr(IndentifierExpr expr);
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
	QLToken token;

	public IndentifierExpr(QLToken token) {
		this.token = token;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitIndetifierExpr(this);
	}
}