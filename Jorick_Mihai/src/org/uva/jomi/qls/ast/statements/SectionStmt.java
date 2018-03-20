package org.uva.jomi.qls.ast.statements;

import org.uva.jomi.qls.ast.QLSToken;

public class SectionStmt extends Stmt {

	private QLSToken name;
	private BlockStmt blockStmt;
	
	public SectionStmt(QLSToken name, BlockStmt blockStmt) {
		this.name = name;
		this.blockStmt = blockStmt;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
