package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;

public class BooleanExpr extends PrimaryExpr {

	public BooleanExpr(QLToken token, QLType type) {
		super(token, type);
	}
	
	public boolean getValue() {
		return Boolean.parseBoolean(this.getLexeme());
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
