package org.uva.jomi.ql.ast.statements;

import java.util.List;

public class BlockStatement extends Statement {
	private final List<Statement> statements;
	
	public BlockStatement(List<Statement> statements) {
		this.statements = statements;
	}

	public List<Statement> getStatements() {
		return statements;
	}
	
	public Statement getStatementAtIndex(int index) {
		return this.statements.get(index);
	}

	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}
}