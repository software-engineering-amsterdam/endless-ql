package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class QuestionStatement extends Statement {
	private final IdentifierExpression identifier;
	private final String label;
	private final QLType type;

	public QuestionStatement(IdentifierExpression identifier, String label, QLType type) {
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

	public IdentifierExpression getIdentifier() {
		return this.identifier;
	}
	
	public int getIdentifierId() {
		return this.identifier.getNodeId();
	}
	
	public String getName() {
		return this.identifier.getName();
	}
	
	public int getIdentifierLineNumber() {
		return this.identifier.getLineNumber();
	}
	public int getIdentifierColumnNumber() {
		return this.identifier.getColumnNumber();
	}
	
	public <T> T visitIdentifierExpr(Expression.Visitor<T> visitor) {
		return this.identifier.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}