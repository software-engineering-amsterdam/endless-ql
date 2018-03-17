package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.QLType;
import org.uva.jomi.ql.ast.expressions.Expression;

public class IfStatement extends Statement {
	private final Expression expression;
	private final BlockStatement ifBlockStatement;
	
	public IfStatement(Expression expression, BlockStatement ifBlockStatement) {
		this.expression = expression;
		this.ifBlockStatement = ifBlockStatement;
	}

	public Expression getExpression() {
		return this.expression;
	}
	
	public int getExpressionLineNumber() {
		return this.expression.getLineNumber();
	}
	
	public int getExpressionColumnNumber() {
		return this.expression.getColumnNumber();
	}
	
	public QLType getExpressionType() {
		return this.expression.getType();
	}
	
	public int getExpressionId() {
		return this.ifBlockStatement.getNodeId();
	}

	public BlockStatement getIfBlockStatement() {
		return this.ifBlockStatement;
	}
	
	public int getIfBlockStatementId() {
		return this.ifBlockStatement.getNodeId();
	}
	
	public <T> T visitExpression(Expression.Visitor<T> visitor) {
		return this.expression.accept(visitor);
	}
	
	public <T> T visitIfBlockStatement(Visitor<T> visitor) {
		return this.ifBlockStatement.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}