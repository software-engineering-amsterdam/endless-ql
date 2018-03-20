package org.uva.jomi.ql.ast.statements;

import org.uva.jomi.ql.ast.expressions.Expression;

public class IfElseStatement extends IfStatement {

	private final BlockStatement elseBlockStatement;
	
	public IfElseStatement(Expression expression, BlockStatement ifBlockStatement, BlockStatement elseBlockStatement) {
		super(expression, ifBlockStatement);
		this.elseBlockStatement = elseBlockStatement;
	}
	
	public BlockStatement getElseBlockStatement() {
		return elseBlockStatement;
	}

	public int getElseBlockStatementId() {
		return this.elseBlockStatement.getNodeId();
	}

	public <T> T visitElseBlockStatement(Visitor<T> visitor) {
		return this.elseBlockStatement.accept(visitor);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}