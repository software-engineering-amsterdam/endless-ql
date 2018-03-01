package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expr;
import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class QuestionStmt extends Stmt {
	private final IdentifierExpr identifier;
	private final String label;
	private final QLType type;

	public QuestionStmt(IdentifierExpr identifier, String label, QLType type) {
		this.identifier = identifier;
		this.label = label;
		this.type = type;
	}

	public String getLabel() {
		return this.label;
	}

	public QLType getType() {
		return this.type;
	}

	public IdentifierExpr getIdentifier() {
		return this.identifier;
	}
	
	public int getIdentifierId() {
		return this.identifier.getId();
	}
	
	public String getIdentifierName() {
		return this.identifier.getName();
	}
	
	public <T> T visitIdentifierExpr(Expr.Visitor<T> visitor) {
		return this.identifier.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}