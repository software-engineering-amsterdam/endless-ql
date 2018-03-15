package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public class IntegerExpr extends PrimaryExpr {

	public IntegerExpr(QLToken token, QLType type) {
		super(token, type);
	}

	public int getValue() {
		return Integer.parseInt(this.getLexeme());
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
