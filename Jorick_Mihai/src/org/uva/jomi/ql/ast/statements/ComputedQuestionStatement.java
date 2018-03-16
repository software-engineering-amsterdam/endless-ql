package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class ComputedQuestionStatement extends QuestionStatement {

	private final Expression expression;
	
	public ComputedQuestionStatement(IdentifierExpression identifier, String label, QLType type, Expression expression) {
		super(identifier, label, type);
		this.expression = expression;
	}
	
	public Expression getExpression() {
		return expression;
	}
	
	public int getExpressionId() {
		return expression.getNodeId();
	}
	
	public QLType getExpressionType() {
		return expression.getType();
	}
	
	public int getExpressionLineNumber() {
		return this.expression.getLineNumber();
	}

	public int getExpressionColumnNumber() {
		return this.expression.getColumnNumber();
	}
	
	public <T> T visitExpression(Expression.Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
