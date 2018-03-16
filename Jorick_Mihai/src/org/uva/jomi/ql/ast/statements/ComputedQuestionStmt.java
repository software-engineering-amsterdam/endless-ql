package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;
import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class ComputedQuestionStmt extends QuestionStmt {

	private final Expression expression;
	
	public ComputedQuestionStmt(IdentifierExpression identifier, String label, QLType type, Expression expression) {
		super(identifier, label, type);
		this.expression = expression;
	}
	
	public Expression getExp() {
		return expression;
	}
	
	public int getExpId() {
		return expression.getNodeId();
	}
	
	public QLType getExprType() {
		return expression.getType();
	}
	
	public int getExprLineNumber() {
		return this.expression.getLineNumber();
	}

	public int getExprColumnNumber() {
		return this.expression.getColumnNumber();
	}
	
	public <T> T visitExpr(Expression.Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}
