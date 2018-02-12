package org.uva.jomi.ql.ast;

public class IndentifierExpr extends Expr {
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