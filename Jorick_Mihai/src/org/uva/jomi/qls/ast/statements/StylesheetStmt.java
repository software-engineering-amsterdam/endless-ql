package org.uva.jomi.qls.ast.statements;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.IdentifierExpr;

public class StylesheetStmt extends Stmt {

	private IdentifierExpr identifier;
	private List<Stmt> pageStatements;
	
	public StylesheetStmt(IdentifierExpr identifier, List<Stmt> pageStatements) {
		this.identifier = identifier;
		this.pageStatements = pageStatements;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
