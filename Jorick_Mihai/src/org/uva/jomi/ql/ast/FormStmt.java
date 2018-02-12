package org.uva.jomi.ql.ast;

public class FormStmt extends Stmt {
	final IndentifierExpr identifier;
	final BlockStmt blockStmt;
	
	public FormStmt(IndentifierExpr identifier, BlockStmt blockStmt) {
		this.identifier = identifier;
		this.blockStmt = blockStmt;
	}

	@Override
	<T> T accept(Visitor<T> visitor) {
		return visitor.visitFormStmt(this);
	}
}