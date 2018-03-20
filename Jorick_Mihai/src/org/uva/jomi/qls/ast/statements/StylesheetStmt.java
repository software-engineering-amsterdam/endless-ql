package org.uva.jomi.qls.ast.statements;

import java.util.List;

import org.uva.jomi.ql.ast.expressions.IdentifierExpression;

public class StylesheetStmt extends Stmt {

	private IdentifierExpression identifier;
	private List<Stmt> pageStatements;
	
	public StylesheetStmt(IdentifierExpression identifier, List<Stmt> pageStatements) {
		this.identifier = identifier;
		this.pageStatements = pageStatements;
	}
	
	@Override
	public <T> T accept(Visitor<T> visitor) {
		return visitor.visit(this);
	}

}
