package org.uva.jomi.ql.ast.expressions;

import org.uva.jomi.ql.ast.QLToken;
import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr.Visitor;

public class StringExpr extends PrimaryExpr {

	public StringExpr(QLToken token, QLType type) {
		super(token, type);
	}
	
	public String getValue() {
		return this.getLexeme();
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
