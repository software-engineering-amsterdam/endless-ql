package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class QuestionStmt extends Stmt {
	public final IdentifierExpr identifier;
	public final String label;
	public final QLType type;

	public QuestionStmt(IdentifierExpr identifier, String label, QLType type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
	}

	public QLType getType() {
		return type;
	}

	public IdentifierExpr getIdentifier() {
		return identifier;
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}