package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public class IdentifierExpr extends Expr {
	public final QLToken token;
	private boolean undefined = true;

	public IdentifierExpr(QLToken token) {
		this.token = token;
	}
	
	public IdentifierExpr(QLToken token, QLType type) {
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
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visitIdentifierExpr(this);
	}
}